package de.gedoplan.showcase.rest;

import de.gedoplan.showcase.domain.Person;
import de.gedoplan.showcase.persistence.PersonRepository;
import de.gedoplan.showcase.service.PersonService;

import java.net.URI;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("persons")
public class PersonResource {

  @Inject
  PersonRepository personRepository;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Person> get() {
    return this.personRepository.findAll();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Transactional
  public Response post(Person person, @Context UriInfo uriInfo) {
    if (person.getId() != null) {
      throw new BadRequestException("Id must not be set");
    }

    this.personRepository.persist(person);

    URI uri = uriInfo
        .getAbsolutePathBuilder()
        .path(person.getId().toString())
        .build();
    return Response
        .created(uri)
        .build();
  }

  @Inject
  PersonService personService;

  @Path("avgAge")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public double getAverageAge() {
    return this.personService.getAverageAge();
  }
}
