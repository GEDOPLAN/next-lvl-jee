# next-lvl-jee
Begleitprojekte zum Vortrag "Migration von JEE-Anwendungen auf Quarkus"

Im Verzeichnis jee liegt eine kleine Beispiel-Anwendung als klassisches JEE-Deployment, z. B. für einen WildFly.
Der Zielserver benötigt eine Datasource mit dem JNDI-Namen java:/jdbc/showcase.

Im Verzeichnis quarkus wird im Vortrag eine äquivalente Anwendung auf Basis von Quarkus aufgebaut, und zwar mit den folgenden Schritten:

. Bootstrap des Projekts
+
siehe Skript `scripts/bootstrap.sh`

.. Build mit `mvn package`

.. Normaler Start mit `java -jar target/quarkus-app/quarkus-run.jar`
+
Stopp mit `Ctrl-C`

.. Dev mode starten mit `mvn quarkus:dev`
+
REST Call `curl localhost:8080/hello`
+
Quellcode (z. B. von `GreetingResource`) ändern und erneuten REST Call ausführen
+
Stopp mit `q`

.. Extension für JPA, Hibernate und PostgreSQL ergänzen
+
siehe Skript `scripts/add-extensions.sh`.
+
Wir verwenden Lombok zum Erzeugen von Gettern, Settern etc. Dazu muss die entsprechende Dependency in `pom.xml` hinzugefügt werden - s. `sniplets\quarkus_lombok.txt`. 
+
Wir lassen die DB-Tabellen automatisch erzeugen. Dazu bitte in `src/main/resources/application.properties` diese Zeile eintragen:
+
`quarkus.hibernate-orm.database.generation=update`
+
Es kann auch schon die gesamte Datei aus `scripts` übernommen werden. Die anderen Einstellungen sind aber (noch)´ nicht wichtig.

.. Einige `curl`-Aufrufe zum Ausprobieren:

 curl -X POST localhost:8080/person \
   -H 'content-type: application/json' \
   -d '{"name": "Dagobert Duck", "birthDay": "1905-12-05"}'
 
 curl -X POST localhost:8080/person \
   -H 'content-type: application/json' \
   -d '{"name": "Donald Duck", "birthDay": "1931-03-13"}'
   
 curl -X POST localhost:8080/person \
   -H 'content-type: application/json' \
   -d '{"name": "Tick Duck", "birthDay": "1940-08-17"}'
   
 curl localhost:8080/person
 
 curl localhost:8080/person/avgAge
 
---

. Ergänzen einer (Default) Datasource für eine PostgreSQL-DB

.. Start der DB als Docker Container:

 docker run -d --rm --name postgres \
   -p 5432:5432 \
   -e POSTGRES_DB=showcase \
   -e POSTGRES_USER=showcase \
   -e POSTGRES_PASSWORD=showcase \
   postgres:11.4
   
.. Ergänzen der Extensions für Hibernate und PostgreSQL im Projekt:

 mvn quarkus:add-extensions \
   -Dextensions=quarkus-hibernate-orm,quarkus-jdbc-postgresql

.. Konfiguration der Datasource in `META-INF\application.properties`:

 quarkus.datasource.db-kind=pg
 quarkus.datasource.jdbc.url=jdbc:postgresql://192.168.80.1:5432/showcase
 quarkus.datasource.username=showcase
 quarkus.datasource.password=showcase
 quarkus.hibernate-orm.database.generation=update
 
. Übernahme der Geschäftslogik aus der klassischen JEE-Anwendung `ssq-jee`

.. Wir verwenden Lombok zum Erzeugen von Gettern, Settern etc. Dazu muss die entsprechende Dependency in `pom.xml` hinzugefügt werden - s. `sniplets\quarkus_lombok.txt`. 

.. Nun können die Pakete `domain`, `persistence`, `rest` und `service` unter `de.gedoplan.showcase` unverändert (!) übernommen werden.

. Ergänzen einiger Unit Tests
 
.. In `sniplets` sind einige Verzeichnisse mit Testklassen, die in `src/test/java/de/gedoplan/showcase` kopiert werden können.

.. Die Tests können klassisch gestartet werden (z. B. in einer IDE).

.. Im Dev Mode kann durch Drücken von `r` in den Continuous Testing Mode gewechselt werden.

. Anwendung im Docker Container

.. Anwendung bauen mit `mvn package`

.. Docker Image erstellen:

 docker build \
   -f src/main/docker/Dockerfile.jvm \
   -t gedoplan-showcase/ssq-quarkus-jvm \
   .

.. Container starten und stoppen:

 docker run -d --rm -p 8080:8080 --name ssq-quarkus-jvm \
   gedoplan-showcase/ssq-quarkus-jvm
   
 docker stop ssq-quarkus-jvm
 
. Native Image

.. Anwendung bauen mit `mvn package -Pnative -Dquarkus.native.container-build=true`

.. Docker Image erstellen:

 docker build \
   -f src/main/docker/Dockerfile.native \
   -t gedoplan-showcase/ssq-quarkus-native \
   .

.. Container starten und stoppen:

 docker run -d --rm -p 8080:8080 --name ssq-quarkus-native \
   gedoplan-showcase/ssq-quarkus-native
   
 docker stop ssq-quarkus-native


Ein thematisch passendes Seminar finden Sie hier: https://gedoplan-it-training.de/java-grundkurse/microservices-mit-quarkus-kompakt/
