# manage-tasks

## Tecnologías utilizadas

- Java 21
- Spring Boot 3.x
- Gradle
- H2 Database (predeterminado)
- JWT (para autenticación)

---

## Descripción del Proyecto

Este proyecto implementa un sistema monolítico para la gestión de tareas, permitiendo a los usuarios crear, asignar,
modificar y consultar tareas, así como autenticar usuarios y proteger endpoints.

---

## Funcionalidades Principales

- **Gestión de Tareas**: Crear, asignar, modificar y consultar tareas.
- **Módulo de Usuarios**: Registro y autenticación con JWT.
- **Arquitectura en Capas** para facilitar la mantenibilidad y escalabilidad.

---

## Requisitos del Sistema

### Módulo de Usuarios

- Registro de nuevos usuarios.
- Autenticación mediante JWT.
- Protección de endpoints críticos.

### Módulo de Tareas

- Creación de tareas con título, descripción, estado, fecha de vencimiento y asignación de usuario.
- Consulta de tareas asignadas a un usuario específico.
- Modificación de tareas existentes.

---

## Configuración y Ejecución

### Configuración de la Base de Datos

Este proyecto incluye configuración predeterminada para H2 y puede usarse con PostgreSQL opcionalmente.

#### H2 Database (predeterminado)

Archivo `application.properties`:

```properties
spring.application.name=gestor-tareas-colaborativo
# Configuración H2
spring.datasource.url=jdbc:h2:mem:tareasdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=admin
spring.datasource.password=123
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
# Consola H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
# Configuración JPA
spring.jpa.show-sql=true
# Puerto del servidor
server.port=${PORT:8080}
```

Acceso a la consola de H2:

[http://localhost:8080/h2-console](http://localhost:8080/h2-console)

#### PostgreSQL (opcional)

Modificar `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/manage_tasks
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
```

Dependencia en `build.gradle`:

```groovy
dependencies {
    runtimeOnly 'org.postgresql:postgresql'
}
```

---

## Compilación y Ejecución

Compilar el proyecto:

```bash
gradle clean build
```

Ejecutar la aplicación:

```bash
gradle bootRun
```

---

## Endpoints Disponibles

Colección de Postman disponible en:

[manage_tasks.postman_collection.json](src/main/resources/manage_tasks.postman_collection.json)

### Usuarios

#### Registro

**POST** `http://localhost:8080/api/auth/register`

Body (JSON):

```json
{
  "userName": "rlealr",
  "password": "12345678RrlR!"
}
```

#### Login

**POST** `http://localhost:8080/api/auth/login`

Body (JSON):

```json
{
  "userName": "rlealr",
  "password": "12345678RrlR!"
}
```

---

### Autenticación

#### Registrar usuario

**POST** `http://localhost:8080/api/auth/register`

Body (JSON):

```json
{
  "userName": "usuario123",
  "password": "PasswordSeguro123!"
}
```

Respuesta:

```json
{
  "data": null,
  "message": "User registered successfully",
  "status": "201"
}
```

#### Iniciar sesión

**POST** `http://localhost:8080/api/auth/login`

Body (JSON):

```json
{
  "userName": "usuario123",
  "password": "PasswordSeguro123!"
}
```

Respuesta:

```json
{
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  },
  "message": "Token generated successfully",
  "status": "200"
}
```

#### Generar token por nombre de usuario

**POST** `http://localhost:8080/api/auth/token?userName=usuario123`

Respuesta:

```json
{
  "data": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "message": "Token generated successfully",
  "status": "200"
}
```

### Tareas

#### Crear tarea

**POST** `http://localhost:8080/api/tareas/crear`

Body (JSON):

```json
{
  "titulo": "Implementar login",
  "descripcion": "Desarrollar módulo de autenticación con JWT",
  "fechaVencimiento": "2025-08-15",
  "usuarioCreadorId": 1,
  "usuarioAsignadoId": 2
}
```

#### Obtener tareas asignadas a un usuario

**GET** `http://localhost:8080/api/tareas/asignadas/{usuarioId}`

#### Modificar tarea

**PUT** `http://localhost:8080/api/tareas/{id}`
