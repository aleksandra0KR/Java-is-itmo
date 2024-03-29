package ru.aleksandra0KR.dao;

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
    return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Person.class, id);
  }

  @Override
  public List<Cat> findAllCats(long id) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

    String sql = "SELECT cat_id FROM Cats WHERE owner_id=:$0";
    Query query = session.createSQLQuery(sql);

    query.setParameter(0, id);

    List<Long> cats_id = (List<Long>) query.getResultList();
    List<Cat> cats = new ArrayList<>();
    var catDao = new CatDaoPostgres();
    for (long a : cats_id) {
      cats.add(catDao.findCatByID(a));
    }
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
