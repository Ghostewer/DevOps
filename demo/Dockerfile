# Usage build : docker build -t demo-image:latest .
# Usage run : docker run -d -p 8080:8080 --name dice-app demo-image:latest

# ==============================================================================
# ÉTAPE 1 : BUILD - Compilation du JAR avec Java 25 JDK
# ==============================================================================

# Utilise une image OpenJDK 25 avec un environnement de build (JDK) basé sur Alpine
# Si l'image exacte n'est pas disponible, elle sera la première à l'être chez Temurin.
FROM maven:3-eclipse-temurin-21 AS builder 

WORKDIR /app

# Copie le fichier de configuration de Maven et le code source
COPY pom.xml .
COPY src ./src

# Exécute la construction directement avec la commande 'mvn'
# Le résultat est le fichier JAR dans /app/target/demo-0.0.1-SNAPSHOT.jar
RUN mvn clean install -DskipTests

# ==============================================================================
# ÉTAPE 2 : RUN - Lancement de l'application avec Java 25 JRE (Image allégée)
# ==============================================================================

# Utilise une image Java Runtime Environment (JRE) 25 pour l'exécution
FROM eclipse-temurin:21-jre-alpine

# Définit le port exposé (par défaut de Spring Boot)
EXPOSE 8080

# Crée et utilise un utilisateur non-root pour des raisons de sécurité
RUN addgroup -S spring && adduser -S spring -G spring
USER spring

# Copie le fichier JAR *spécifique* de l'étape 'builder' vers l'image finale
# Nom de fichier spécifié : demo-0.0.1-SNAPSHOT.jar
COPY --from=builder /app/target/demo-0.0.1-SNAPSHOT.jar /app/app.jar

# Définit la commande de lancement de l'application
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]