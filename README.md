# Foro Hub - Backend API

Foro Hub es una API REST desarrollada en Java y Spring Boot que gestiona un sistema de foros, permitiendo registrar, consultar, actualizar y eliminar tópicos, así como autenticar usuarios con JWT. Utiliza Flyway para versionamiento de base de datos y Swagger para documentación.

---

## 🚀 Tecnologías Utilizadas

- Java JDK 17
- Spring Boot 3.3.0
- MySQL 8.0.43.0

## 🧰 Herramientas de Desarrollo

- IntelliJ IDEA Community Edition
- Git
- MySQL Workbench 8.0
- Apache Maven

---

## 📂 Estructura del Proyecto

### Código fuente (`src/main/java`)

```text
com.foro.hub.api
├── ApiApplication.java
├── controller
│   ├── AutenticacionController.java
│   ├── HelloController.java
│   ├── TopicoController.java
│   └── UsuarioController.java
├── domain
│   ├── ValidacionException.java
│   ├── curso
│   │   ├── Curso.java
│   │   └── CursoRepository.java
│   ├── topico
│   │   ├── Topico.java, Repository, Service, DTOs
│   └── usuario
│       ├── Usuario.java, Perfil.java
│       ├── Repository, Service, DTOs
├── infra
│   ├── errores/TratadorDeErrores.java
│   ├── security/TokenService, Filter, Config, etc.
│   └── springdoc/SpringDocConfiguration.java
```

---

### Recursos (`src/main/resources`)

```text
application.properties
application-prod.properties
db/migration/
├── V1__create-tablas-usuario-perfil.sql
├── V2__create-tabla-curso.sql
├── V3__create-tabla-topico.sql
└── V4__create-tabla-respuesta.sql
```

---

## 📷 Swagger - Operaciones Disponibles

![Operaciones Swagger](https://lh3.google.com/u/1/d/1AZN2YG9plIi1atLTrrIGccaPJU59QxwQ=w1920-h1080-iv1?auditContext=forDisplay)

---

## 🗃️ Diagrama de Base de Datos

![Diagrama MySQL](https://lh3.google.com/u/1/d/1aV3qxpnnXWHDFyU82434oIT0ArRxdT78=w1920-h868-iv1?auditContext=prefetch)

---

## ⬇️ Clonar y Compilar el Proyecto

```bash
git clone https://github.com/abernal90/challenge-foro-hub.git
cd challenge-foro-hub
mvn clean package -DskipTests
```

Esto generará el archivo `.jar` en la carpeta `target/`.

---

## ▶️ Ejecutar la Aplicación desde el `.jar`

### Windows

```bash
java -jar target/api-0.0.1-SNAPSHOT.jar ^
  --spring.profiles.active=prod ^
  --spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub ^
  --spring.datasource.username=tu_usuario ^
  --spring.datasource.password=tu_contraseña
```

### Linux / MacOS

```bash
java -jar target/api-0.0.1-SNAPSHOT.jar   --spring.profiles.active=prod   --spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub   --spring.datasource.username=tu_usuario   --spring.datasource.password=tu_contraseña
```

---

## 🔐 Seguridad

La API implementa autenticación basada en JWT. Para usar endpoints protegidos en Swagger:

1. Ejecuta `POST /login` con usuario válido.
2. Copia el valor de `token` del cuerpo de respuesta.
3. Haz clic en **Authorize** en Swagger UI.
4. Ingresa: `Bearer <token>` y pulsa **Authorize**.

---

## 👤 Autor

Desarrollado por [@abernal90](https://github.com/abernal90) como parte del proyecto **Challenge Foro Hub** por [Alura Latan](https://app.aluracursos.com/)
