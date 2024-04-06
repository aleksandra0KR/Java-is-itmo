package ru.aleksandra0KR.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;
import ru.aleksandra0KR.dao.PersonDao;
import ru.aleksandra0KR.dao.PersonPostgresDao;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.dto.PersonDto;
import ru.aleksandra0KR.entity.Cat;
import ru.aleksandra0KR.entity.Person;
import ru.aleksandra0KR.mapper.CatMapper;
import ru.aleksandra0KR.mapper.PersonMapper;

@NoArgsConstructor
public class PersonServiceImplementation implements PersonService {

  private final PersonDao personDao = new PersonPostgresDao();

  @Override
  public PersonDto findPersonByID(long id) {
    var person = personDao.findPersonByID(id);
    if (person == null) {
      throw new NullPointerException("person does not exist");
    }
    return PersonMapper.asDto(person);
  }

  @Override
  public List<CatDto> findAllCats(long id) {
    var person = personDao.findPersonByID(id);
    if (person == null) {
      throw new NullPointerException("person does not exist");
    }
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
    var personD = personDao.findPersonByID(person.getId());
    if (personD == null) {
      throw new NullPointerException("person does not exist");
    }
    personDao.deletePerson(personD);
  }
}
