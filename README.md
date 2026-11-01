# PingMe

A bright, responsive real-time chat demo built with Java 17, Spring Boot, and native WebSockets.

## Features
- Responsive, animated dark/glass UI with neon gradients
- Registration and login demo flow
- Passwords hashed with BCrypt (not stored in plaintext)
- Real-time room chat using WebSockets
- No database setup required for local demo
- Docker multi-stage build
- Maven wrapper and GitHub Actions-friendly structure

## Requirements
- Java 17
- Docker (optional)
- Internet access on the first Maven build if dependencies are not cached

## Run locally
On Windows PowerShell:
```powershell
.\mvnw.cmd clean verify
.\mvnw.cmd spring-boot:run
```
Then open http://localhost:8080.

On Linux/macOS:
```bash
chmod +x ./mvnw
./mvnw clean verify
./mvnw spring-boot:run
```

Create an account with a username (3–24 characters) and password (8–72 characters). Open another browser/private window to create another account and chat between sessions.

## Build and run with Docker
```bash
docker build -t pingme:local .
docker run --rm -p 8080:8080 pingme:local
```

## API
- `POST /api/auth/register`
- `POST /api/auth/login`
- WebSocket endpoint: `/ws/chat`

Example request:
```json
{"username":"moonlight","password":"not-a-real-password"}
```

## Important limitations
This is a learning/demo project, not production-ready authentication. User accounts are kept in memory and disappear on restart. The chat room is public, messages are not persisted, and the WebSocket currently trusts the username sent by the browser. Before production, implement authenticated sessions or JWT validation for WebSocket connections, persistent storage, rate limiting, restrictive CORS/origin rules, and security review. Update Spring Boot and dependencies to supported patched versions before a public deployment.
