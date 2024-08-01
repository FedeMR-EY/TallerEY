FROM eclipse-temurin:21-jdk-alpine
LABEL name=ms-tarjetas

RUN apk add --no-cache bash
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
RUN mkdir -p /app/ms-tarjetas && chown javauser:javauser /app/ms-tarjetas

WORKDIR /app/ms-tarjetas
USER javauser

COPY target/*.jar /app/ms-tarjetas/ms-tarjetas.jar

ENTRYPOINT ["java", "-jar", "./ms-tarjetas.jar"]