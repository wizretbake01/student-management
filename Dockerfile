# Stage 1: Build stage
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Kopjo pom.xml files
COPY pom.xml .
COPY model/pom.xml model/
COPY dto/pom.xml dto/
COPY mapper/pom.xml mapper/
COPY repository/pom.xml repository/
COPY service/pom.xml service/
COPY excel/pom.xml excel/
COPY web/pom.xml web/

# Download dependencies
RUN mvn dependency:go-offline

# Kopjo source code
COPY model/src model/src
COPY dto/src dto/src
COPY mapper/src mapper/src
COPY repository/src repository/src
COPY service/src service/src
COPY excel/src excel/src
COPY web/src web/src

# Build application
RUN mvn clean package -DskipTests

# Stage 2: Runtime stage
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Krijo user jo-root për siguri
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Kopjo JAR nga build stage
COPY --from=build /app/web/target/web-1.0.0.jar app.jar

# Eksposo portin
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Run aplikacionin
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
