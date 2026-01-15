# ---------- BUILD STAGE ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /build

# Copy only pom first (cache dependencies)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the JAR
RUN mvn clean package -DskipTests

# ---------- RUNTIME STAGE ----------
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the final JAR
COPY --from=build /build/target/storage-monitoring-api.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
