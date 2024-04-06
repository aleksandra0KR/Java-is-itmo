package ru.aleksandra0KR.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.aleksandra0KR.entity.Cat;
import ru.aleksandra0KR.entity.Person;
import ru.aleksandra0KR.hibernate.HibernateSessionFactoryUtil;

public class PersonPostgresDao implements PersonDao {

  private final Session session;
  private final Transaction transaction;
  public PersonPostgresDao(Session session, Transaction transaction) {
    this.session = session;
    this.transaction = transaction;
  }

  @Override
  public Person findPersonByID(long id) {
    Person person = session.get(Person.class, id);
    transaction.commit();
    session.close();
    return person;
  }

  @Override
  public List<Cat> findAllCats(long id) {
    String sql = "SELECT id FROM Cats WHERE person = ?";
    Query query = session.createSQLQuery(sql);
    query.setParameter(1, id);

    List<BigInteger> cats_id = query.list();
    List<Cat> cats = new ArrayList<>();
    var catDao = new CatDaoPostgres(session, transaction);
    for (var a : cats_id) {
      cats.add(catDao.findCatByID(a.longValue()));
    }

    transaction.commit();
    session.close();
    return cats;
  }

  @Override
  public long addPerson(Person person) {
    session.save(person);
    transaction.commit();
    session.close();
    return person.getPerson_id();
  }

  @Override
  public void updatePerson(Person person) {
    session.update(person);
    transaction.commit();
    session.close();
  }

  @Override
  public void deletePerson(Person person) {
    session.delete(person);
    transaction.commit();
    session.close();
  }
}
