package de.gedoplan.showcase.persistence;

import de.gedoplan.showcase.domain.Person;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class PersonRepository {
  @Inject
  EntityManager entityManager;

  public void persist(Person person) {
    this.entityManager.persist(person);
  }

  public Person findById(Integer id) {
    return this.entityManager.find(Person.class, id);
  }

  public List<Person> findAll() {
    return this.entityManager
      .createQuery("select x from Person x", Person.class)
      .getResultList();
  }
}
