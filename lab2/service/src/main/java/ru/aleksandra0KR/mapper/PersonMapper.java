package ru.aleksandra0KR.mapper;

import java.util.ArrayList;
import java.util.Objects;
import lombok.experimental.UtilityClass;
import ru.aleksandra0KR.dto.PersonDto;
import ru.aleksandra0KR.entity.Person;

@UtilityClass

public class PersonMapper {

  public PersonDto asDto(Person person) {
    Objects.requireNonNull(person);
    return new PersonDto(person.getPerson_id(), person.getName(), person.getBirthdate());
  }

  public Person asDao(PersonDto person) {
    Objects.requireNonNull(person);
    return new Person(person.getId(), person.getName(), person.getBirthDate(), new ArrayList<>());
  }
}
