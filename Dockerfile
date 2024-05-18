FROM adoptopenjdk/openjdk11
WORKDIR /app/
COPY . .
RUN ./mvnw clean package

FROM adoptopenjdk/openjdk11
WORKDIR /deploy/
COPY --from=0 /app/target/roxy-api-server-1.0.0.jar /deploy/roxy-api-server-1.0.0.jar
COPY --from=0 /app/whatap.conf /deploy/

#RUN mkdir -p /whatap
#COPY --from=whatap/kube_mon /data/agent/micro/whatap.agent.kube.jar /whatap
#COPY ./whatap.conf /whatap/

ENV SPRING_OPTION=""
#ENTRYPOINT exec java -javaagent:/whatap/whatap.agent.kube.jar -Dwhatap.micro.enabled=true -jar ${SPRING_OPTION} roxy-api-server-1.0.0.jar
ENTRYPOINT exec java -jar ${SPRING_OPTION} roxy-api-server-1.0.0.jar

