package ru.aleksandra0KR.service;

import java.time.LocalDate;
import java.util.List;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.dto.PersonDto;

public interface PersonService {

  PersonDto findPersonByID(long id);

  PersonDto findPersonByName(String name);

  List<CatDto> findAllCats(long id);

  PersonDto addPerson(PersonDto person);

  void updatePerson(PersonDto person);

  void deletePerson(Long id);
}
