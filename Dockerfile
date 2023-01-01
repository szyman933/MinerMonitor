FROM openjdk:17.0.2-jdk
ADD build/libs/miner_monitor-1.0.1.jar .
EXPOSE 8080
CMD java -jar miner_monitor-1.0.1.jar