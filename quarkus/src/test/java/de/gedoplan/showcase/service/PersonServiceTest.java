package de.gedoplan.showcase.service;

import de.gedoplan.showcase.persistence.PersonRepositoryMock;

import jakarta.inject.Inject;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class PersonServiceTest {

  @Inject
  PersonService personService;

  @Test
  void testGetAverageAge() {
    assertEquals(PersonRepositoryMock.getAvgAge(), this.personService.getAverageAge(), 0.1);
  }
}
