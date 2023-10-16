docker run -d --rm -p 5432:5432 -e POSTGRES_DB=showcase -e POSTGRES_USER=showcase -e POSTGRES_PASSWORD=showcase --name postgres_showcase postgres:16.0-alpine
