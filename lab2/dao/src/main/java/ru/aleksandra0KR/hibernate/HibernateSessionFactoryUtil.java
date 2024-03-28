package ru.aleksandra0KR.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.aleksandra0KR.entity.Cat;
import ru.aleksandra0KR.entity.Person;

public class HibernateSessionFactoryUtil {

  private final static SessionFactory sessionFactory = initSessionFactory();

  private static SessionFactory initSessionFactory() {
    try {
      return new Configuration().configure().buildSessionFactory();
    }
    catch (Throwable ex){
      System.err.println("aboba" + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

  public static SessionFactory getSessionFactory() {

    return sessionFactory;
  }

  public static void close() {
    getSessionFactory().close();
  }
}
