package ru.aleksandra0KR.controller;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.dto.PersonDto;
import ru.aleksandra0KR.service.PersonService;
import ru.aleksandra0KR.service.PersonServiceImplementation;

@NoArgsConstructor
@AllArgsConstructor
public class PersonController {
  private PersonService personService;

  public PersonDto createPerson(String name, LocalDate birthDate) {
    PersonDto personDto = personService.addPerson(name, birthDate);
    System.out.println("The Person has been created with id: " + personDto.getId());
    return personDto;
  }

  public PersonDto findPersonByID(long id) {
    PersonDto person = personService.findPersonByID(id);
    System.out.println("Tht Person has been found" + person.toString());
    return person;
  }

  public List<CatDto> findAllCatsByPersonID(long id) {
    List<CatDto> catsDto = personService.findAllCats(id);
    for (CatDto cat : catsDto) {
      System.out.println(cat.toString());
    }
    return catsDto;
  }

  public PersonDto changeName(PersonDto person, String name) {
    person.setName(name);
    personService.updatePerson(person);
    PersonDto newPerson = personService.findPersonByID(person.getId());
    System.out.println("The name has been changed to" + newPerson.getName());
    return newPerson;
  }

  public PersonDto changeBirthdate(PersonDto person, LocalDate date) {
    person.setBirthDate(date);
    personService.updatePerson(person);
    PersonDto newPerson = personService.findPersonByID(person.getId());
    System.out.println("The birthdate has been changed to " + newPerson.getBirthDate());
    return newPerson;
  }

  public void deletePerson(PersonDto person) {
    System.out.println("The person has been deleted");
    personService.deletePerson(person);
  }
}
