package de.gedoplan.showcase.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import de.gedoplan.showcase.persistence.PersonRepositoryMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class PersonServiceTest {

  @Inject
  PersonService personService;

  @Test
  void testGetAverageAge() {
    assertEquals(PersonRepositoryMock.getAvgAge(), this.personService.getAverageAge(), 0.1);
  }
}
