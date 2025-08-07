# Usamos una imagen base de OpenJDK 17
FROM openjdk:17-jdk-slim AS build

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml (para Maven) o build.gradle (para Gradle)
# Este paso es útil si necesitas instalar dependencias en el contenedor durante el build

COPY build.gradle .

# Copiar el código fuente
COPY src ./src

# Ejecutar el comando para compilar la aplicación

# Si usas Gradle, usa este comando en su lugar
RUN ./gradlew build

# Establecemos la imagen de ejecución para la aplicación
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo para el contenedor
WORKDIR /app

# Copiar el archivo JAR desde el paso de construcción
COPY --from=build /app/build/libs/*.jar ./app.jar

# Exponer el puerto en el que la aplicación escuchará
EXPOSE 8080

# Definir el comando para ejecutar el JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
