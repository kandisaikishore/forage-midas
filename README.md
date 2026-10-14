# Forage Midas — Midas Core

[![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)](https://www.java.com/) [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-6DB33F?logo=springboot)](https://spring.io/projects/spring-boot) [![Maven](https://img.shields.io/badge/Build-Maven-C71A36?logo=apachemaven)](https://maven.apache.org/) [![Repository](https://img.shields.io/badge/GitHub-Source-181717?logo=github)](https://github.com/kandisaikishore/forage-midas)

A Java 17 and Spring Boot project for the Forage Midas Core exercise. The repository combines a Spring web application with JPA-based data access and Kafka integration to support transaction-oriented exercise workflows.

## Technology stack

- Java 17
- Spring Boot 3.2.5
- Spring Web
- Spring Data JPA
- Apache Kafka / Spring Kafka
- H2 in-memory database for local/test configuration
- Maven and Spring Boot Test

## Project structure

- `src/main/java/com/jpmc/midascore/` — application source
- `component/` — application integration components
- `entity/` — domain entities
- `foundation/` — core domain objects such as balances and transactions
- `repository/` — data-access interfaces
- `src/test/java/` — exercise and integration tests
- `application.yml` — local server, datasource, and Kafka configuration
- `pom.xml` — dependencies and build configuration

## Prerequisites

Install:

- JDK 17
- Git
- Maven (or use the included Maven Wrapper)
- Kafka only when running workflows that require a Kafka broker; review the test configuration and project setup first

## Run locally

Clone the repository and switch to the exercise branch:

```bash
git clone https://github.com/kandisaikishore/forage-midas.git
cd forage-midas
git switch flow
```

Build and run the test suite with the Maven Wrapper:

**macOS/Linux**
```bash
./mvnw test
```

**Windows**
```powershell
.\mvnw.cmd test
```

To start the Spring Boot application:

**macOS/Linux**
```bash
./mvnw spring-boot:run
```

**Windows**
```powershell
.\mvnw.cmd spring-boot:run
```

The checked-in configuration sets the application port to `33400` and uses an in-memory H2 datasource. Kafka-dependent operations may require a broker or the embedded broker used by the tests.

## Configuration notes

- The local H2 datasource is configured in `application.yml`; its data is transient.
- Kafka's bootstrap address and the configured topic are also specified in that file.
- Use environment variables or a local untracked configuration for secrets if you add external services. Never commit passwords or tokens.

## Quality checks

Run `./mvnw test` (or `mvnw.cmd test` on Windows) before proposing a change. Add tests for transaction processing, persistence mapping, message production/consumption, and invalid or boundary inputs when modifying those paths.

## Scope and attribution

This repository is associated with a Forage exercise. Follow the exercise's terms and respect any restrictions on proprietary materials or redistribution. Do not assume the project is a production-ready financial system.

## Contributing

Make small, reviewable changes. Include the problem being addressed, implementation notes, and the tests run.

## License

Follow the applicable Forage exercise terms; no separate open-source license is assumed by this README.
