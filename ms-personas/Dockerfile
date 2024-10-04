FROM eclipse-temurin:21-jdk-alpine
LABEL name=ms-personas

# Instalar bash y curl
RUN apk add --no-cache bash curl
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
RUN mkdir -p /app/ms-personas && chown javauser:javauser /app/ms-personas

WORKDIR /app/ms-personas
USER javauser

COPY target/*.jar /app/ms-personas/ms-personas.jar

ENTRYPOINT ["java", "-jar", "./ms-personas.jar"]