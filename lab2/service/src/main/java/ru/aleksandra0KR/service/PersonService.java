package ru.aleksandra0KR.service;

import java.time.LocalDate;
import java.util.List;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.dto.PersonDto;

public interface PersonService {

  PersonDto findPersonByID(long id);

  List<CatDto> findAllCats(long id);

  PersonDto addPerson(String name, LocalDate birthday);

  void updatePerson(PersonDto person);

  void deletePerson(PersonDto person);
}
