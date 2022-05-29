package de.gedoplan.showcase.persistence;

import de.gedoplan.showcase.domain.Person;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@ApplicationScoped
@Alternative
@Priority(1)
public class PersonRepositoryMock extends PersonRepository {

  /**
   * Test persons.
   */
  public static final Person[] PERSONS = {
    new Person("Hugo", LocalDate.of(1998, 1, 15)),
    new Person("Otto", LocalDate.of(2002, 2, 10))
  };

  /**
   * Retrieve avarage age of test persons.
   * @return average age
   */
  public static double getAvgAge() {
    return Arrays.stream(PERSONS)
      .mapToInt(Person::getAge)
      .average()
      .getAsDouble();
  }

  @Override
  public List<Person> findAll() {
    return Arrays.asList(this.PERSONS);
  }

  @Override
  public void persist(Person person) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Person findById(Integer id) {
    throw new UnsupportedOperationException();
  }

}
