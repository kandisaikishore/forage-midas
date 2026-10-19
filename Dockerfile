# Build and verify the application with Java 17.
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml ./
COPY .mvn/ .mvn/
COPY mvnw ./
RUN chmod +x mvnw
COPY application.yml ./
COPY src/ src/
RUN mvn --batch-mode --no-transfer-progress -Dtest=UserRecordTest,TaskOneTests verify

# Small runtime image; Render supplies PORT at runtime.
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
RUN useradd --system --uid 10001 appuser
COPY --from=build /workspace/target/midas-core-1.0.0.jar /app/app.jar
COPY --from=build /workspace/application.yml /app/application.yml
USER appuser
EXPOSE 10000
ENV PORT=10000
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75.0", "-jar", "/app/app.jar"]
