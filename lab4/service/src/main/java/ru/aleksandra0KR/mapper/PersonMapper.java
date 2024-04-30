package ru.aleksandra0KR.mapper;

import java.util.ArrayList;
import java.util.Objects;
import lombok.experimental.UtilityClass;
import ru.aleksandra0KR.dto.OwnerDto;
import ru.aleksandra0KR.dto.PersonDto;
import ru.aleksandra0KR.model.Owner;
import ru.aleksandra0KR.model.Person;

@UtilityClass
public class PersonMapper {
  public PersonDto asDto(Person person) {
    Objects.requireNonNull(person);
    return new PersonDto(person.getUsername(), person.getPassword());
  }

  public Person asDao(PersonDto person) {
    Objects.requireNonNull(person);
    return new Person(person.getUsername(), person.getPassword(), person.getRoles());
  }
}
