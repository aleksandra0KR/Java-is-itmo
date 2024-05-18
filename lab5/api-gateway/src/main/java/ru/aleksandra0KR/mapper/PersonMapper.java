package ru.aleksandra0KR.mapper;

import java.util.Objects;
import lombok.experimental.UtilityClass;
import ru.aleksandra0KR.dao.Person;
import ru.aleksandra0KR.dto.PersonDto;


@UtilityClass
public class PersonMapper {

  public PersonDto asDto(Person person) {
    Objects.requireNonNull(person);
    return new PersonDto(person.getUsername(), person.getPassword(), person.getRoles(),
        person.getOwnerId());
  }

  public Person asDao(PersonDto person) {
    Objects.requireNonNull(person);
    return new Person(person.getUsername(), person.getPassword(), person.getRoles(),
        person.getOwnerID());
  }
}
