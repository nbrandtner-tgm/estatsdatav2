FROM openjdk:17
WORKDIR /estats
COPY target/estatsdata-1.0-SNAPSHOT-jar-with-dependencies.jar /estats/data.jar
#COPY start.sh .
#RUN chmod +x /estats/start.sh
CMD ["java", "-jar", "/estats/data.jar"]
#ENTRYPOINT ["/estats/start.sh"]