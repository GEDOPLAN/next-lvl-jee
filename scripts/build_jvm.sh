mvn package -DskipTests=true
docker build -f src/main/docker/Dockerfile.jvm -t gedoplan-showcase/next-lvl-quarkus:jvm .