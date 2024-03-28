package ru.aleksandra0KR.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ru.aleksandra0KR.dao.PersonDao;
import ru.aleksandra0KR.dao.PersonPostgresDao;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.dto.PersonDto;
import ru.aleksandra0KR.entity.Cat;
import ru.aleksandra0KR.entity.Person;
import ru.aleksandra0KR.mapper.CatMapper;
import ru.aleksandra0KR.mapper.PersonMapper;

public class PersonServiceImplementation implements PersonService {

  private final PersonDao personDao = new PersonPostgresDao();

  @Override
  public PersonDto findPersonByID(long id) {
    return PersonMapper.asDto(personDao.findPersonByID(id));
  }

  @Override
  public List<CatDto> findAllCats(long id) {
    var cats = personDao.findAllCats(id);
    List<CatDto> catsDto = new ArrayList<>();
    for (Cat cat : cats) {
      catsDto.add(CatMapper.asDto(cat));
    }
    return catsDto;
  }

  @Override
  public PersonDto addPerson(String name, LocalDate birthday) {
    Person person = new Person(name, birthday);
    long id = personDao.addPerson(person);
    PersonDto personDto = PersonMapper.asDto(person);
    personDto.setId(id);
    return personDto;
  }

  @Override
  public void updatePerson(PersonDto person) {
    personDao.updatePerson(PersonMapper.asDao(person));
  }

  @Override
  public void deletePerson(PersonDto person) {
    personDao.deletePerson(PersonMapper.asDao(person));
  }
}
