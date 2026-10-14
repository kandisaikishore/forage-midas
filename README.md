# Forage Midas — Midas Core

[![Java 17](https://img.shields.io/badge/Java-17-orange?logo=openjdk)](https://www.java.com/) [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-6DB33F?logo=springboot)](https://spring.io/projects/spring-boot) [![Maven](https://img.shields.io/badge/Build-Maven-C71A36?logo=apachemaven)](https://maven.apache.org/) [![Source](https://img.shields.io/badge/Source-GitHub-181717?logo=github)](https://github.com/kandisaikishore/forage-midas)

A Java 17 and Spring Boot implementation workspace for the Forage Midas Core exercise. The project combines Spring web components, JPA-oriented data access, and Kafka integration for transaction-processing learning workflows.

## Technology stack

- Java 17
- Spring Boot 3.2.5 and Spring Web
- Spring Data JPA
- Apache Kafka / Spring Kafka
- H2 in-memory database for local/test configuration
- Maven and Spring Boot Test

## Project structure

- `src/main/java/com/jpmc/midascore/` — application source
- `component/` — integration components
- `entity/` — domain entities
- `foundation/` — core transaction and balance objects
- `repository/` — data-access interfaces
- `src/test/java/` — tests
- `application.yml` — server, datasource, and Kafka configuration
- `pom.xml` — dependencies and build

## Prerequisites

Install JDK 17 and Git. Use the included Maven Wrapper. Kafka may be needed for workflows requiring a broker; consult the test setup before starting external services.

## Run locally

```bash
git clone https://github.com/kandisaikishore/forage-midas.git
cd forage-midas
git switch flow
```

Run tests:

**macOS/Linux**
```bash
./mvnw test
```

**Windows PowerShell**
```powershell
.\mvnw.cmd test
```

Start the application:

**macOS/Linux**
```bash
./mvnw spring-boot:run
```

**Windows PowerShell**
```powershell
.\mvnw.cmd spring-boot:run
```

The checked-in configuration uses port `33400` and an in-memory H2 datasource. Kafka-dependent workflows may require a broker or the embedded broker configured for tests.

## Quality checks

Run the test suite before making changes. Add coverage for transaction-processing rules, entity/repository behaviour, message production/consumption, invalid inputs, and boundary conditions.

## Scope and attribution

This repository is associated with a Forage exercise. Respect the exercise terms and any restrictions on proprietary material or redistribution. It should not be treated as a production financial system.

## Contributing

Keep changes focused and reviewable. Describe the behaviour changed and include tests run.

## License

Follow the applicable Forage exercise terms; this README does not assume a separate open-source license.
