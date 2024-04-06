package ru.aleksandra0KR.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.aleksandra0KR.entity.Cat;
import ru.aleksandra0KR.entity.Person;

public interface PersonDao {

  Person findPersonByID(long id, Session session);

  List<Cat> findAllCats(long id, Session session);

  long addPerson(Person person, Session session);

  void updatePerson(Person person, Session session);

  void deletePerson(Person person, Session session);
}