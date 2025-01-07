FROM openjdk:21-jdk-slim AS builder

WORKDIR /app

COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew clean bootJar --no-daemon

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

COPY docker-entrypoint.sh /usr/local/bin/docker-entrypoint.sh
COPY wait-for-it.sh /usr/local/bin/wait-for-it.sh

RUN chmod +x /usr/local/bin/docker-entrypoint.sh \
    && chmod +x /usr/local/bin/wait-for-it.sh

ENTRYPOINT ["docker-entrypoint.sh"]

CMD ["java", "-jar", "/app/app.jar"]