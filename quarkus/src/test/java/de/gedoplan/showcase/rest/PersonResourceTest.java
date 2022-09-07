package de.gedoplan.showcase.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import de.gedoplan.showcase.persistence.PersonRepositoryMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class PersonResourceTest {

  @Test
  void testGetAverageAge() {
    given()
      .when().get("/api/persons/avgAge")
      .then()
      .statusCode(200)
      .body(is(Double.toString(PersonRepositoryMock.getAvgAge())));
  }
}
