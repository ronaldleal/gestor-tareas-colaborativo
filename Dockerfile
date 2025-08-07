# Usamos una imagen base de OpenJDK 17
FROM openjdk:17-jdk-slim AS build

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo build.gradle (y otros archivos necesarios para Gradle)
COPY build.gradle .
#COPY settings.gradle .
COPY gradlew .
COPY gradle /app/gradle

# Dar permisos de ejecución a gradlew
RUN chmod +x gradlew

# Copiar el código fuente
COPY src ./src

# Ejecutar el comando para compilar la aplicación
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
