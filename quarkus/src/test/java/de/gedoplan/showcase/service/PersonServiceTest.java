package de.gedoplan.showcase.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

import de.gedoplan.showcase.domain.Person;
import de.gedoplan.showcase.persistence.PersonRepositoryMock;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import java.util.Arrays;

@QuarkusTest
public class PersonServiceTest {

  @Inject
  PersonService personService;

  @Test
  void testGetAverageAge() {
    assertEquals(PersonRepositoryMock.getAvgAge(), this.personService.getAverageAge(), 0.1);
  }
}
