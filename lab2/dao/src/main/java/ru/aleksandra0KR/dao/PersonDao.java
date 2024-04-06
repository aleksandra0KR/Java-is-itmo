package ru.aleksandra0KR.dao;

import java.util.List;
import org.hibernate.Transaction;
import ru.aleksandra0KR.entity.Cat;
import ru.aleksandra0KR.entity.Person;

public interface PersonDao {

  Person findPersonByID(long id);

  List<Cat> findAllCats(long id);

  long addPerson(Person person);

  void updatePerson(Person person);

  void deletePerson(Person person);
}