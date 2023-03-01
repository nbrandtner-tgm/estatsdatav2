FROM openjdk:17
WORKDIR /estats
COPY target/estatsdata-1.0-SNAPSHOT-jar-with-dependencies.jar /estats/data.jar
COPY start.sh /estats/
RUN chmod +x /estats/start.sh
ENTRYPOINT ["/estats/start.sh"]
