package de.gedoplan.showcase.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import de.gedoplan.showcase.domain.Person;
import de.gedoplan.showcase.persistence.PersonRepositoryMock;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import java.util.Arrays;

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
