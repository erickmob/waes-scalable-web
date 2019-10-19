FROM openjdk:8-jdk-alpine
ENV JASYPT_KEY="1byte"
VOLUME /tmp
ARG DEPENDENCY=target/dependency

COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.waes.diff.v1.api.ApiApplication", "-Djasypt.encryptor.password=$JASYPT_KEY"]