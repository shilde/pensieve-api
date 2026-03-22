# pensieve-api

REST API for [Pensieve](https://github.com/shcreative/pensieve) — a self-hosted bookmark manager with semantic search.

Built with Spring Boot 4 and Kotlin. Delegates embedding and semantic search to [pensieve-mind](https://github.com/shcreative/pensieve-mind) (Python service).

## Tech Stack

- **Runtime:** Java 21, Kotlin 2.2
- **Framework:** Spring Boot 4.0
- **Database:** PostgreSQL 17 + Flyway migrations
- **Auth:** OAuth2 / JWT via Authentik
- **Semantic search:** WebClient integration to `pensieve-mind`

## Prerequisites

- Java 21+
- Docker (for local development via Docker Compose)
- A running `pensieve-mind` instance

## Local Development

Start the PostgreSQL database:

```bash
docker compose up -d
```

Run the application with the `local` profile:

```bash
./gradlew bootRun --args='--spring.profiles.active=local'
```

The `local` profile (`application-local.yml`) overrides the required environment variables. Create the file if it does not exist yet:

```yaml
# src/main/resources/application-local.yml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/pensieve
    username: pensieve
    password: pensieve

pensieve:
  mind:
    url: http://localhost:8081
```

## Configuration

The application is configured via environment variables:

| Variable              | Description                              |
|-----------------------|------------------------------------------|
| `DATABASE_URL`        | JDBC URL, e.g. `jdbc:postgresql://...`   |
| `DATABASE_USER`       | Database username                        |
| `DATABASE_PASSWORD`   | Database password                        |
| `AUTHENTIK_ISSUER_URI`| OAuth2 issuer URI for JWT validation     |
| `MIND_URL`            | Base URL of the pensieve-mind service    |

## API Endpoints

### Bookmarks — `/api/bookmarks`

| Method | Path                  | Description                          |
|--------|-----------------------|--------------------------------------|
| POST   | `/api/bookmarks`      | Create a bookmark                    |
| GET    | `/api/bookmarks`      | List bookmarks (paginated, optional `collectionId` filter) |
| GET    | `/api/bookmarks/{id}` | Get a bookmark by ID                 |
| PUT    | `/api/bookmarks/{id}` | Update a bookmark                    |
| DELETE | `/api/bookmarks/{id}` | Delete a bookmark                    |

### Collections — `/api/collections`

| Method | Path                     | Description               |
|--------|--------------------------|---------------------------|
| POST   | `/api/collections`       | Create a collection       |
| GET    | `/api/collections`       | List collections (paginated) |
| GET    | `/api/collections/{id}`  | Get a collection by ID    |
| PUT    | `/api/collections/{id}`  | Update a collection       |
| DELETE | `/api/collections/{id}`  | Delete a collection       |

### Tags — `/api/tags`

| Method | Path             | Description         |
|--------|------------------|---------------------|
| GET    | `/api/tags`      | List all tags       |
| GET    | `/api/tags/{id}` | Get a tag by ID     |
| DELETE | `/api/tags/{id}` | Delete a tag        |

Tags are created automatically when referenced in a bookmark.

### Search — `/api/search`

| Method | Path          | Description                                               |
|--------|---------------|-----------------------------------------------------------|
| GET    | `/api/search` | Search bookmarks by query (`q`), optional `collectionId`  |

Uses semantic search via `pensieve-mind` when available, falls back to text search otherwise.

### Health — `/actuator/health`

Available without authentication.

## Architecture

Layered clean architecture per module:

```
Controller (DTO) → Service (BO) → Repository interface → RepositoryImpl → JpaRepository (Entity)
```

Mappers are implemented as Kotlin extension functions.

```
src/main/kotlin/de/shcreative/pensieve/
├── bookmark/       # Bookmark CRUD
├── collection/     # Collection CRUD
├── tag/            # Tag management
├── search/         # Search (semantic + fallback)
├── mind/           # WebClient integration to pensieve-mind
└── config/         # Security & WebClient configuration
```

## Build

```bash
./gradlew build
```
