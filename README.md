# WAES Assignemnt - Scalable Web 

![CircleCI](https://img.shields.io/circleci/build/github/johnjonathan/waes-scalable-web/master)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=johnjonathan_waes-scalable-web&metric=alert_status)](https://sonarcloud.io/dashboard?id=johnjonathan_waes-scalable-web)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=johnjonathan_waes-scalable-web&metric=security_rating)](https://sonarcloud.io/dashboard?id=johnjonathan_waes-scalable-web)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=johnjonathan_waes-scalable-web&metric=coverage)](https://sonarcloud.io/dashboard?id=johnjonathan_waes-scalable-web)
[![GitHub license](https://img.shields.io/github/license/johnjonathan/waes-scalable-web)](https://github.com/johnjonathan/waes-scalable-web/blob/master/LICENSE)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=johnjonathan_waes-scalable-web&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=johnjonathan_waes-scalable-web)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=johnjonathan_waes-scalable-web&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=johnjonathan_waes-scalable-web)

A simple REST API that shows the differences between ***left*** and ***right*** side. 

### Prerequisites

    - OpenJDK 1.8 
    - Gradle 5.x
    - MongoDB 3.x (Optional)
    - Docker (Optional)

### Installing

Clone the repository

```zsh
me@waes git clone git@github.com:johnjonathan/waes-scalable-web.git
```
Build the application 

```zsh
me@waes ./gradlew build -x test
```

Run the application
```zsh
me@waes ./gradlew bootRun
```

Calling the ***Left*** side endpoint
```zsh
me@waes curl --request POST \
  --url http://localhost:8080/v1/diff/uid/left \
  --header 'content-type: application/json' \
  --data '{
	"content":"a29tcGFyYXRpb24gc2l0ZQ=="
}'
----------------------------------------------------
Response
----------------------------------------------------
{
  "message": "Payload [id:uid] created",
  "status": "CREATED"
}
```
 Calling the ***Right*** side endpoint
```zsh
me@waes curl --request POST \
  --url http://localhost:8080/v1/diff/uid/right \
  --header 'content-type: application/json' \
  --data '{
	"content":"Y29tcGFyYXRpb24gc2l6ZQ=="
}'
```
Calling the **Diff** endpoint:

```zsh
me@waes curl --request GET \
  --url http://localhost:8080/v1/diff/uid
----------------------------------------------------
Response
----------------------------------------------------
{
  "result": "NOT_EQUAL",
  "details": [
    {
      "offset": 0,
      "length": 1
    },
    {
      "offset": 19,
      "length": 1
    }
  ]
}
```

## Running the tests
To run the tests just execute the gradle test task
```zsh
   me@waes ./gradlew test
```

## Built With

* [Spring Boot 2](https://spring.io/projects/spring-boot) - The web framework used
* [Gradle 5.6.2](https://gradle.org/) - Dependency Management
* [Swagger 2](https://swagger.io/solutions/getting-started-with-oas/) - REST API design and documentation
* [JUnit 5](https://junit.org/junit5/) - Unit and Integration Tests
* [Mockito 3](https://site.mockito.org/) - Mock for unit tests
* [Hamcrest 2](http://hamcrest.org/JavaHamcrest/) - Matchers for unit tests
* [MongoDB](https://www.mongodb.com/download-center/community) - Database
* [Docker](https://www.mongodb.com/download-center/community) - Containers

## Versioning

I use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/johnjonathan/waes-scalable-web/tags). 

## Authors

* **John Silva** - *Initial work* 

## License

This project is licensed under the Apache 2.0 License - see the [LICENSE.md](LICENSE.md) file for details
