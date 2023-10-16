package de.gedoplan.showcase.webui;

import de.gedoplan.showcase.domain.Person;
import de.gedoplan.showcase.persistence.PersonRepository;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class PersonPresenter implements Serializable {

  @Getter
  private List<Person> persons;

  @Getter
  @Setter
  private Person selectedPerson;

  @Inject
  PersonRepository personRepository;

  @PostConstruct
  void init() {
    this.persons = personRepository.findAll();
  }

  public void openNew() {
    this.selectedPerson = new Person(null, null);
  }

  @Transactional
  public void savePerson() {
    if (this.selectedPerson.getId() == null) {
      this.personRepository.persist(this.selectedPerson);
      this.persons.add(this.selectedPerson);
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Person added"));
    } else {
      this.personRepository.merge(this.selectedPerson);
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Person updated"));
    }

    PrimeFaces.current().executeScript("PF('managePersonDialog').hide()");
    PrimeFaces.current().ajax().update("form:messages", "form:dt-persons");
  }

  @Transactional
  public void deletePerson() {
    this.personRepository.removeById(this.selectedPerson.getId());
    this.persons.remove(this.selectedPerson);
    this.selectedPerson = null;
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Person removed"));
    PrimeFaces.current().ajax().update("form:messages", "form:dt-persons");
  }

}
