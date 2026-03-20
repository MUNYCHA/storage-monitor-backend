# Storage Monitor Backend

REST API for monitoring server storage usage, built with Spring Boot 4 and MySQL.

## Requirements

- Java 17+
- Maven 3.9+
- MySQL
- Docker & Docker Compose (for containerized deployment)

## Configuration

Copy the example env file and fill in your values:

```bash
cp .env.example .env
```

| Variable | Description |
|---|---|
| `DB_URL` | JDBC URL to your MySQL database |
| `DB_USERNAME` | Database username |
| `DB_PASSWORD` | Database password |

## Running Locally

```bash
./mvnw spring-boot:run
```

## Building

```bash
./mvnw clean package -DskipTests
```

## Deployment

### Option 1 — Docker Compose (recommended)

**1. Copy and edit the env file**
```bash
cp .env.example .env
nano .env
```

Fill in your values:
```
DB_URL=jdbc:mysql://your-db-host:3306/your-db-name
DB_USERNAME=your-db-username
DB_PASSWORD=your-db-password
```

**2. Build and start**
```bash
docker compose up --build -d
```

Docker Compose reads `.env` and passes the variables into the container. The app picks them up via `application.yml`:
```
DB_URL → spring.datasource.url
DB_USERNAME → spring.datasource.username
DB_PASSWORD → spring.datasource.password
```

To stop:
```bash
docker compose down
```

To view logs:
```bash
docker compose logs -f
```

After a code change, rebuild and restart:
```bash
docker compose up --build -d
```

### Option 2 — JAR

**1. Copy and edit the env file**
```bash
cp .env.example .env
nano .env
```

**2. Build and run with env vars**
```bash
./mvnw clean package -DskipTests

DB_URL=jdbc:mysql://your-db-host:3306/your-db-name \
DB_USERNAME=your-db-username \
DB_PASSWORD=your-db-password \
java -jar target/storage-monitoring-api.jar
```

If env vars are not set, the app falls back to the default values defined in `application.yml`.

## API Endpoints

Base URL: `/api/server-storage-usage`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/server-storage-usage` | Get all storage snapshots |
| GET | `/api/server-storage-usage/{id}` | Get snapshot by ID |
| GET | `/api/server-storage-usage/latest` | Get latest snapshot per server |
