# next-lvl-jee
Begleitprojekte zum Vortrag "Migration von JEE-Anwendungen auf Quarkus"

Im Verzeichnis jee liegt eine kleine Beispiel-Anwendung als klassisches JEE-Deployment, z. B. für einen WildFly.
Der Zielserver benötigt eine Datasource mit dem JNDI-Namen java:/jdbc/showcase.

Im Verzeichnis quarkus wird im Vortrag eine äquivalente Anwendung auf Basis von Quarkus aufgebaut, und zwar mit den folgenden Schritten:


. Bootstrap des Projekts, Prod Mode, Dev Mode
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


. Verwaltung von Personen ergänzen (JPA, REST)

.. Extension für JPA, Hibernate und PostgreSQL ergänzen
+
siehe Skript `scripts/add-extensions.sh`.
+
Wir verwenden Lombok zum Erzeugen von Gettern, Settern etc. Dazu muss die entsprechende Dependency in `pom.xml` hinzugefügt werden - s. `sniplets/lombok.txt`. 
+
Wir lassen die DB-Tabellen automatisch erzeugen. Dazu bitte in `src/main/resources/application.properties` diese Zeile eintragen:
+
`quarkus.hibernate-orm.database.generation=update`
+
Es kann auch schon die gesamte Datei aus `scripts` übernommen werden. Die anderen Einstellungen sind aber (noch)´ nicht wichtig.
+
Anwendung wieder im Dev Mode starten: `mvn quarkus:dev`.

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

 
. Test-Unterstützung
+
In `scripts/src/test` befinden sich einige Test-Klassen, die als Projekt-Sourcen übernommen werden können.
+
Start der Tests
* in der IDE
* per Maven (`mvn test`)
* mittels Continuous Testing im Dev Mode (`r`).


. Web-UI auf Basis von JSF

.. Dependencies für MyFaces und PrimeFaces ergänzen
+
siehe `sniplets/myfaces_primefaces.txt`.

.. UI-Code aus klassischer Anwendung (unverändert) übernehmen
+
* Paket `de.gedoplan.showcase.webui` mit Klasse `PersonPresenter`
* Descriptor `web.xml` aus `src/main/webapp` nach `src/main/resources/META-INF
* Web-Dateien aus `src/main/webapp` nach `src/main/resources/META-INF/resources`


---

Ein thematisch passendes Seminar finden Sie hier:

https://gedoplan-it-training.de/java-grundkurse/microservices-mit-quarkus-kompakt/
