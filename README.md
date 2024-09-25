Solution was written on Java 21 with Spring Boot. Maven is needed to build and run it.
To run the program, just go to the project directory and run

mvn spring-boot:run

After that, open the browser and go to http://localhost:8080/api/players to see all players
or http://localhost:8080/api/players/{playerID} with existing playerID (for example http://localhost:8080/api/players/overtdi01) to see one player.
In case there is no player with a given ID, it will be an HTTP 404 error.

There are also some unit tests to check parsing players from text.


Some ideas about improvements:

Store data about players in the database (like PostgreSQL for example). We could have a special parser which parses CSV and stores parsed data in the database (or maybe several parsers for different sources of input data). It will allow extending an API - add ability to add new players or modify existing, add search abilities - for example select players by country of birth or year of birth, etc.

When data is stored in the database, we will need in-memory cache to improve performance.

Create some UI for results of requests and 404 page.

To improve performance and reliability of the system, we could use several instances of this service running simultaneously and use a load balancer to split incoming requests between them.

When there will be several instances of the service, we will need a distributed cache solution, like Hazelcast or Redis.
