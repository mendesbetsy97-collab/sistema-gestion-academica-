# Sistema de Gestión Académica — GA7-220501096-AA2-EV01

Módulo web de estudiantes desarrollado con Java 17, Jakarta Servlets/JSP, JDBC y MySQL. Incluye inserción, consulta, actualización y eliminación (CRUD).

## Requisitos
- JDK 17+
- Apache Maven
- MySQL 8+
- Apache Tomcat 10.1+

## Preparación
1. Abra MySQL Workbench y ejecute `database/sistema_gestion_academica.sql`.
2. Abra `src/main/java/co/edu/sena/sgac/config/DatabaseConnection.java` y configure usuario y contraseña de MySQL.
3. En una terminal, desde la carpeta del proyecto, ejecute `mvn clean package`.
4. Copie `target/SistemaGestionAcademica.war` a la carpeta `webapps` de Tomcat 10.1 y encienda el servidor.
5. Abra `http://localhost:8080/SistemaGestionAcademica/estudiantes`.

No abra los JSP directamente desde el explorador: la aplicación debe ejecutarse dentro de Tomcat y MySQL debe estar encendido.

## Versionamiento Git
Ejecute `git status` para revisar el estado del proyecto. Para agregar los archivos a un repositorio ya configurado, use `git add .` y `git commit -m "Codificación módulo estudiantes JDBC"`. Consulte la URL real con `git remote -v`.
