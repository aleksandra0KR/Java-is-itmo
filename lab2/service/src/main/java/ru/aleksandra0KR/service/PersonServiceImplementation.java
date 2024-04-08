package ru.aleksandra0KR.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.aleksandra0KR.dao.PersonDao;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.dto.PersonDto;
import ru.aleksandra0KR.entity.Cat;
import ru.aleksandra0KR.entity.Person;
import ru.aleksandra0KR.hibernate.HibernateSessionFactoryUtil;
import ru.aleksandra0KR.mapper.CatMapper;
import ru.aleksandra0KR.mapper.PersonMapper;


public class PersonServiceImplementation implements PersonService {

  private PersonDao personDao;

  public PersonServiceImplementation(PersonDao personDao){
    this.personDao = personDao;
  }
  Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

  @Override
  public PersonDto findPersonByID(long id) {
    Transaction transaction = session.beginTransaction();
    var person = personDao.findPersonByID(id, session);
    if (person == null) {
      throw new NullPointerException("person does not exist");
    }
    transaction.commit();
    return PersonMapper.asDto(person);
  }

  @Override
  public List<CatDto> findAllCats(long id) {
    Transaction transaction = session.beginTransaction();
    var person = personDao.findPersonByID(id, session);
    if (person == null) {
      throw new NullPointerException("person does not exist");
    }
    var cats = personDao.findAllCats(id, session);
    List<CatDto> catsDto = new ArrayList<>();
    for (Cat cat : cats) {
      catsDto.add(CatMapper.asDto(cat));
    }
    transaction.commit();
    return catsDto;
  }

  @Override
  public PersonDto addPerson(String name, LocalDate birthday) {
    Transaction transaction = session.beginTransaction();
    Person person = new Person(name, birthday);
    long id = personDao.addPerson(person, session);
    PersonDto personDto = PersonMapper.asDto(person);
    personDto.setId(id);
    transaction.commit();
    return personDto;
  }

  @Override
  public void updatePerson(PersonDto person) {
    Transaction transaction = session.beginTransaction();
    personDao.updatePerson(PersonMapper.asDao(person), session);
    transaction.commit();
  }

  @Override
  public void deletePerson(PersonDto person) {
    Transaction transaction = session.beginTransaction();
    var personD = personDao.findPersonByID(person.getId(), session);
    if (personD == null) {
      throw new NullPointerException("person does not exist");
    }
    personDao.deletePerson(personD, session);
    transaction.commit();
  }
}
