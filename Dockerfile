FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /workspace

COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

COPY src ./src
RUN mvn -q -DskipTests package dependency:copy-dependencies

FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /workspace/target/DistributedProxySystem-1.0-SNAPSHOT.jar /app/app.jar
COPY --from=build /workspace/target/dependency /app/lib
COPY entrypoint.sh /app/entrypoint.sh

RUN chmod +x /app/entrypoint.sh

# Railway folosește variabila PORT din env, nu EXPOSE
ENTRYPOINT ["/app/entrypoint.sh"]
CMD ["labs.partea1.DWServer"]