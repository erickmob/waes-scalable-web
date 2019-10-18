/*
 * Copyright 2015-2019 John Silva.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package com.waes.diff.v1.api.service;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waes.diff.v1.api.domain.enums.Direction;
import com.waes.diff.v1.api.domain.enums.Result;
import com.waes.diff.v1.api.domain.exception.PayloadForComparisonNotFound;
import com.waes.diff.v1.api.domain.model.DiffDetails;
import com.waes.diff.v1.api.domain.model.PayloadData;
import com.waes.diff.v1.api.domain.model.PayloadDiffResult;
import com.waes.diff.v1.api.repository.PayloadRepository;
import com.waes.diff.v1.api.repository.entity.Payload;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch.Diff;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayloadDiffService implements DiffService {

  private final PayloadRepository payloadRepository;

  /**
   * Searches for the {@code id} run validations and return {@code PayloadDiffResult}
   *
   * @param id of the left and right encoded JSON content
   * @return {@code PayloadDiffResult} with the comparison details.
   */
  @Override
  public PayloadDiffResult getDiff(String id) {
    List<Payload> payloads = payloadRepository.findPayloadById(id);
    return compareSides(getPayloadSidesData(payloads));
  }

  /**
   * Receives a {@code Payload} with both Left and Right sides. Compares the content and returns a
   * {@code PayloadDiffResult}
   *
   * @param payloadData payload data
   * @return {@code PayloadDiffResult} with the comparison details.
   */
  @Override
  public PayloadDiffResult compareSides(PayloadData payloadData) {
    if (payloadData.isNoneBlank()) {
      if (payloadData.isEqual()) {
        return PayloadDiffResult.create().result(Result.EQUAL);
      } else if (payloadData.sizeDosNotMatch()) {
        return PayloadDiffResult.create().result(Result.SIZE_NOT_MATCH);
      } else {
        return PayloadDiffResult.create()
            .result(Result.NOT_EQUAL)
            .addDetails(getDiffDetails(payloadData));
      }
    } else {
      throw new PayloadForComparisonNotFound();
    }
  }

  /**
   * Filter and returns a list with all differences
   *
   * @param payloadData A list of {@code PayloadData}
   * @return {@code DiffDetails} list
   */
  @Override
  public LinkedList<DiffDetails> getDiffDetails(PayloadData payloadData) {
    LinkedList<Diff> diff =
        new DiffMatchPatch().diffMain(payloadData.getLeft(), payloadData.getRight());
    return diff.stream()
        .filter(e -> e.operation.equals(DiffMatchPatch.Operation.DELETE))
        .map(m -> new DiffDetails(offset(diff, m), m.text.length()))
        .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   * Extract left and right sides from a {@code List<Payload>} and merge the results into a {@code
   * PayloadData} object.
   *
   * @param payloads {@code List<Payload>} of payload to be transformed into {@code PayloadData}
   * @return Payload data with Left and Right encoded strings
   */
  private PayloadData getPayloadSidesData(List<Payload> payloads) {
    Map<Direction, String> map =
        payloads.stream().collect(toMap(Payload::getDirection, Payload::getContent));
    PayloadData payloadData = new ObjectMapper().convertValue(map, PayloadData.class);
    return ofNullable(payloadData).orElseThrow(PayloadForComparisonNotFound::new);
  }

  /**
   * @param diffs List with all differences
   * @param diff The difference needed to have its index found
   * @return The index of diff location
   */
  private int offset(LinkedList<Diff> diffs, Diff diff) {
    return new DiffMatchPatch().diffText1(diffs).indexOf(diff.text);
  }
}
