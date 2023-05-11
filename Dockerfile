# Usa la imagen base de Java 11
FROM openjdk:11
# Copia tu aplicación al contenedor
COPY "./target/Cuentas-0.0.1-SNAPSHOT.jar" "cuentas.jar"

# Expone el puerto 8080
EXPOSE 8080

# Inicia tu aplicación
CMD ["java", "-jar", "cuentas.jar"]