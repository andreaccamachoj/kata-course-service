# 🎓 Microservicio de Cursos – CoE Development Platform

## Antes de Iniciar

El **microservicio de Cursos** forma parte de la plataforma **CoE Development Platform**, diseñada para permitir que los colaboradores puedan subir, consultar y gestionar capacitaciones técnicas, recibir insignias digitales 🏅 y hacer seguimiento a su progreso de aprendizaje.

Este servicio está desarrollado en **Java 17 + Spring Boot 3**, implementando **Arquitectura Limpia (Clean Architecture)**, y se conecta a una base de datos **PostgreSQL** y un bucket **AWS S3** para el manejo de archivos.

Lee el artículo 👉 [Clean Architecture – Aislando los detalles](https://medium.com/bancolombia-tech/clean-architecture-aislando-los-detalles-4f9530f35d7a)

# Arquitectura

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## Domain

El módulo **Domain** encapsula las **reglas del negocio** relacionadas con cursos, capítulos, insignias y progreso de usuario.

### Contiene:
- **Modelos del dominio:** `Course`, `Chapter`, `Module`, `Badge`, `UserCourseProgress`, `UserChapterProgress`, `UserBadge`.
- **Interfaces gateway:** Definen los puertos de interacción con la base de datos y servicios externos (como el Auth Service).
- **Excepciones de negocio y técnicas:** Control de errores del dominio.

## Usecases

Define los **casos de uso principales** de la aplicación y orquesta las interacciones entre el dominio, los repositorios y servicios externos.

### Casos de uso implementados:
- Creación de cursos y capítulos.
- Asignación de cursos a usuarios.
- Registro del progreso del usuario.
- Asignación automática de insignias al completar cursos.
- Consulta de cursos, módulos, insignias y progreso.

Los casos de uso operan de forma imperativa y no dependen de frameworks o bases de datos.

## Infrastructure

Contiene las implementaciones concretas de los puertos definidos en el dominio.  
Se organiza en tres submódulos: **Helpers**, **Driven Adapters** y **Entry Points**.


### Driven Adapters

Implementaciones concretas de conexión con fuentes externas:
- **JPA Adapters:** Persistencia en PostgreSQL para cursos, capítulos, módulos e insignias.
- **S3 Adapter:** Conexión con AWS S3 para subir y descargar archivos (PDF, videos, guías).
- **REST Adapter:** Comunicación con el microservicio de Autenticación para validar tokens y obtener usuarios.

### Entry Points

Exponen los endpoints públicos del microservicio a través de controladores REST.

#### Endpoints principales:

| Método | Endpoint | Descripción |
|--------|-----------|-------------|
| `GET` | `/api/v1/modules` | Lista todos los módulos |
| `GET` | `/api/v1/courses` | Lista todos los cursos |
| `GET` | `/api/v1/courses/module/{moduleId}` | Cursos por módulo |
| `GET` | `/api/v1/courses/{courseId}` | Detalle de curso |
| `POST` | `/api/v1/courses` | Crea curso con capítulos y archivos |
| `POST` | `/api/v1/training/coursesassign` | Asigna un curso a un usuario |
| `POST` | `/api/v1/training/chapters/complete` | Marca capítulo como completado |
| `GET` | `/api/v1/training/courses/progress/{userId}` | Progreso de cursos del usuario |
| `GET` | `/api/v1/training/courses/{courseId}/progress/{userId}` | Progreso específico de un curso |
| `GET` | `/api/v1/badges/me/{userId}` | Insignias del usuario |
| `GET` | `/api/v1/badges/{courseId}/badge` | Insignia por curso |
| `POST` | `/api/v1/badges/assingBadge` | Asignar manualmente una insignia a usuario |

## Application

Contiene la inicialización y configuración principal del microservicio.  
Es el módulo más externo y responsable de **arrancar la aplicación**.

### Responsabilidades:
- Configuración de **Spring Boot** y beans.
- Definición de controladores REST.
- Escaneo automático de componentes (`@ComponentScan`).
- Integración con el microservicio de Autenticación.
- Configuración de AWS S3 y JPA.

## Ejecución Local

### ⚙️ Requisitos previos

| Requisito | Versión mínima |
|------------|----------------|
| **Java JDK** | 17 |
| **PostgreSQL** | 15 o superior |
| **Gradle** | 8.x |
| **Cuenta AWS S3** | (para subir archivos) |

### 🧾 Configuración del entorno

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/andreaccamachoj/kata-course-service
   ```

2. **Crear la base de datos:**
   Ejecutar el archivo init.sql que se encuentra en: `deployment/db/init_db.sql`

3. **Configurar `application.yml`:**
   ```yaml
   server:
     port: 8081

   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/training
       username: postgres
       password: postgres
       driver-class-name: org.postgresql.Driver
     jpa:
       hibernate:
         ddl-auto: update
       show-sql: true

   adapter:
     restconsumer:
       url: http://localhost:8080/api/v1/auth/user

   aws:
     s3:
       bucket-name: coe-training-content
       region: us-east-1
   ```

### ▶️ Compilación y ejecución

1. **Compilar el proyecto:**
   ```bash
   ./gradlew clean build
   ```

2. **Ejecutar la aplicación:**
   ```bash
   java -jar build/libs/course.jar
   ```

3. **Verificar el servicio:**
   ```
   http://localhost:8081/api/v1/courses
   ```

## Flujo Funcional

1. El usuario inicia sesión en el **microservicio de autenticación** y obtiene su JWT.
2. El **frontend React** envía el token al microservicio de cursos.
3. El microservicio valida el token llamando al Auth Service.
4. Los cursos se cargan y almacenan en AWS S3.
5. El usuario completa capítulos → el progreso se actualiza.
6. Al completar un curso, se asigna automáticamente una insignia.

## Variables de Entorno

| Variable | Descripción                            |
|-----------|----------------------------------------|
| `URL_DB` | URL de la base de datos de PostgreSQL  |
| `USERNAME_DB` | Usuario de base de datos               |
| `PASSWORD_DB` | Contraseña de base de datos            |
| `AWS_REGION` | Región AWS                             |
| `BUCKET_NAME` | Nombre del bucket S3                   |
| `AUTH_SERVICE_URL` | URL del microservicio de autenticación |

## Autor

Desarrollado por **Andrea C.**