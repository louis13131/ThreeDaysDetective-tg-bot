FROM maven:3.9-eclipse-temurin-21 AS build

COPY pom.xml /app/

COPY src /app/src/

WORKDIR /app

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/target/ThreeDaysDetective-tg-bot-1.0-SNAPSHOT.jar /app/bot.jar

ENTRYPOINT ["java", "-jar", "bot.jar"]
CMD ["${BOT_TOKEN}", "telegram"]