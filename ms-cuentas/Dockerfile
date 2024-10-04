FROM eclipse-temurin:21-jdk-alpine
LABEL name=ms-cuentas

# Instalar bash y curl
RUN apk add --no-cache bash curl
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
RUN mkdir -p /app/ms-cuentas && chown javauser:javauser /app/ms-cuentas

WORKDIR /app/ms-cuentas
USER javauser

COPY target/*.jar /app/ms-cuentas/ms-cuentas.jar

ENTRYPOINT ["java", "-jar", "./ms-cuentas.jar"]