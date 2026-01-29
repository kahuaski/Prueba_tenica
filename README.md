# Prueba Técnica — Resumen

Resumen del trabajo realizado en este repositorio (Prueba_tenica).

## Tecnologías
- Java 21, Spring Boot
- Maven
- PostgreSQL (en contenedores Docker)
- RabbitMQ (en contenedores Docker)
- Docker / Docker Compose

## ¿Qué se implementó?
- Microservicios:
  - `ms-account-service` (API de cuentas, movimientos y reportes)
  - `ms-customer-service` (API de clientes)
- Endpoints y base path `/api` configurado para ambos servicios.
- Registro de movimientos que actualiza saldo y persiste transacciones.
- Manejo de insuficiencia de fondos:
  - Lanza `InsufficientFundsException` con el mensaje exacto **"Saldo no disponible"**.
  - `GlobalExceptionHandler` devuelve HTTP 422 con ese texto.
- Endpoint de reportes de estado de cuenta por rango de fechas y cliente (devuelve JSON).
- Se agregaron pruebas (unitarias e integración) en los módulos correspondientes.
- Se añadieron `.gitignore` en la raíz y en cada microservicio para evitar artefactos locales.

## Endpoints principales

- ms-account-service (base): `http://localhost:8082/api`
  - `POST /movimientos` — Registrar movimiento (actualiza saldo). Ejemplo payload:
    ```json
    {"accountNumber":"ACC123","movementType":"DEBIT","amount":-50.0}
    ```
    - Respuestas: `201 Created` con `Movement` o `422` con cuerpo `Saldo no disponible`.
  - `GET /movimientos` — Listar movimientos
  - `GET /movimientos/account/{accountNumber}` — Movimientos por cuenta
  - `GET /movimientos/account/{accountNumber}/fecha?inicio={ISO}&fin={ISO}` — Movimientos por rango
  - `GET /reportes?cliente={cliente}&inicio={ISO}&fin={ISO}` — Reporte de estado de cuenta (JSON)
  - `GET/POST/PUT/DELETE /cuentas` — Gestión de cuentas

- ms-customer-service (base): `http://localhost:8081/api`
  - `GET /clientes` — Listar clientes
  - `GET /clientes/{clientId}` — Obtener cliente
  - `POST /clientes` — Crear cliente
  - `PUT /clientes/{clientId}` — Actualizar
  - `DELETE /clientes/{clientId}` — Eliminar

## Formato de reportes
- El endpoint `GET /reportes` devuelve JSON con la estructura:

```json
{
  "cliente": "Juan",
  "cuentas": [
    {
      "accountNumber": "ACC123",
      "accountType": "AHORROS",
      "balance": 1200.5,
      "movimientos": [
        {"date":"2025-01-05T10:00:00","movementType":"CREDIT","amount":500.0,"balance":1500.5},
        {"date":"2025-01-20T15:30:00","movementType":"DEBIT","amount":-300.0,"balance":1200.5}
      ]
    }
  ]
}
```

Parámetros: `inicio` y `fin` (ISO 8601 datetimes), `cliente` opcional.

## Cómo ejecutar localmente
1. Levantar la stack con Docker Compose (desde la raíz del repo):

```bash
cd C:\Users\dcahuasqui\Desktop\prueba_tecnica
docker compose up -d
```

2. Verificar contenedores:

```bash
docker compose ps
```

3. Probar endpoints (ejemplo):

```bash
curl "http://localhost:8082/api/reportes?inicio=2025-01-01T00:00:00&fin=2025-01-31T23:59:59"
curl "http://localhost:8081/api/clientes"
```

4. Ejecutar tests por servicio (Windows):

```bash
cd ms-account-service
mvnw.cmd test
cd ..\ms-customer-service
mvnw.cmd test
```

## Notas y recomendaciones
- El manejo de error para saldo insuficiente devuelve `422` y el mensaje claro "Saldo no disponible".
- Si prefieres aceptar un único parámetro `fecha` con formato `inicio,fin` podría añadirse como alias; actualmente usamos `inicio` y `fin` separados.
- Se añadieron `.gitignore` para evitar subir `target/`, IDE files y artefactos.

## Control de versiones
- Los cambios fueron comiteados y la rama `main` fue pusheada a `origin/main`.

