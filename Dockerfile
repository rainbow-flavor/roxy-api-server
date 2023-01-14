FROM adoptopenjdk/openjdk11
WORKDIR /app/
COPY . .
CMD ["./mvnw", "clean", "package"]

FROM adoptopenjdk/openjdk11
WORKDIR /deploy/
COPY --from=0 /app/target/roxy-api-server-1.0.0.jar /deploy/roxy-api-server-1.0.0.jar
ENV SPRING_OPTION=""
ENTRYPOINT exec java -jar ${SPRING_OPTION} roxy-api-server-1.0.0.jar