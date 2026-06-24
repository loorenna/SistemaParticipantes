# Sistema de Registro de Participantes

## Descripción

Aplicación de escritorio desarrollada en Java utilizando JavaFX y PostgreSQL que permite gestionar un registro de participantes mediante un CRUD completo (Crear, Leer, Actualizar y Eliminar). Además, implementa un sistema de autenticación, validaciones de datos y patrones de diseño.

## Tecnologías utilizadas

- Java JDK 26
- JavaFX
- PostgreSQL
- JDBC
- Maven
- IntelliJ IDEA

## Patrones de diseño implementados

- **Singleton:** Utilizado en la clase `Conexion` para mantener una única instancia de conexión con la base de datos.
- **Factory:** Utilizado para la creación de objetos `Participante`.
- **Strategy:** Utilizado para la validación del correo electrónico.

## Funcionalidades

- Inicio de sesión (Login).
- Registro de participantes.
- Consulta de participantes.
- Actualización de información.
- Eliminación de registros.
- Validación de campos obligatorios.
- Validación de edad.
- Validación de cédula.
- Validación de correo electrónico.
- Prevención de correos duplicados.
- Visualización de registros mediante TableView.

## Base de datos

Nombre de la base de datos:

```
participantesdb
```

Tabla utilizada:

```
participantes
```

## Requisitos

- Java JDK 26
- PostgreSQL
- IntelliJ IDEA
- Maven

## Ejecución

1. Crear la base de datos `participantesdb`.
2. Ejecutar el script SQL incluido en el proyecto.
3. Configurar el usuario y contraseña de PostgreSQL en la clase `Conexion`.
4. Abrir el proyecto en IntelliJ IDEA.
5. Esperar que Maven descargue las dependencias.
6. Ejecutar la clase `Main`.

## Usuario de prueba

Usuario:

```
admin
```

Contraseña:

```
1234
```

## Autor

**Nombre:** Alisson Quiguango

Escuela Politécnica Nacional - ESFOT

Carrera de Tecnología Superior en Desarrollo de Software
