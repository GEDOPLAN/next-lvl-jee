mvn package -Pnative -Dquarkus.native.container-build=true -DskipTests=true
docker build -f src/main/docker/Dockerfile.native -t gedoplan-showcase/next-lvl-quarkus:native .