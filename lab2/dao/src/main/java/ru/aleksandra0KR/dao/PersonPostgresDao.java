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

  @Override
  public Person findPersonByID(long id) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    Person person = session.get(Person.class, id);
    transaction.commit();
    session.close();
    return person;
  }

  @Override
  public List<Cat> findAllCats(long id) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();

    String sql = "SELECT id FROM Cats WHERE person = ?";
    Query query = session.createSQLQuery(sql);
    query.setParameter(1, id);

    List<BigInteger> cats_id = query.list();
    List<Cat> cats = new ArrayList<>();
    var catDao = new CatDaoPostgres();
    for (var a : cats_id) {
      cats.add(catDao.findCatByID(a.longValue()));
    }

    transaction.commit();
    session.close();
    return cats;
  }

  @Override
  public long addPerson(Person person) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    session.save(person);
    transaction.commit();
    session.close();
    return person.getPerson_id();
  }

  @Override
  public void updatePerson(Person person) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    session.update(person);
    transaction.commit();
    session.close();
  }

  @Override
  public void deletePerson(Person person) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    session.delete(person);
    transaction.commit();
    session.close();
  }
}
