# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
# Build
./mvnw clean package
./mvnw clean package -DskipTests

# Run
./mvnw spring-boot:run
java -jar target/storage-monitoring-api.jar

# Test
./mvnw test
./mvnw test -Dtest=ClassName        # single test class
./mvnw test -Dtest=ClassName#method # single test method

# Docker
docker compose up --build -d
docker compose down
docker compose logs -f
```

## Architecture

Spring Boot 4 REST API (Java 17, Maven) that serves server storage metrics. Connects to a MySQL database via environment variables defined in `.env`.

**Layered structure** under `com.munycha.storagemonitor`:

- `controller/` — REST endpoints at `/api/server-storage-usage`
- `service/` — Business logic (interface + impl pattern), entity→DTO mapping via `toDto()` helper
- `repository/` — `ServerStorageSnapshotRepository` (JpaRepository + custom `@Query`), `ServerStorageSnapshotQueryRepository` (EntityManager + JPQL for latest snapshot query)
- `entity/` — `ServerStorageSnapshot` (parent, one-to-many lazy) → `MountPathStorageUsage` (child)
- `dto/` — `ServerStorageSnapshotDto`, `MountPathStorageUsageDto` — the only types exposed to the controller and frontend
- `config/` — CORS config (allows `http://localhost:5173`, GET only on `/api/**`)

**Key data model:** `ServerStorageSnapshot` is a point-in-time capture of a server's storage, identified by `systemId`/`serverIp`. It has a lazy `@OneToMany` collection of `MountPathStorageUsage` records ordered by path. All repository queries use `LEFT JOIN FETCH` to avoid N+1.

**Entity→DTO boundary:** Entities never leave the service layer. The controller only imports DTOs.

## Configuration

`src/main/resources/application.yml` reads from environment variables with fallback defaults:

```yaml
url: ${DB_URL:jdbc:mysql://192.168.60.137:3306/logDB}
username: ${DB_USERNAME:root}
password: ${DB_PASSWORD:2004}
```

Copy `.env.example` to `.env` and fill in real values before running via Docker Compose.
