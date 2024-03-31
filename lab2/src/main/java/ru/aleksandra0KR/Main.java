package ru.aleksandra0KR;

import java.time.LocalDate;
import org.hibernate.Session;
import ru.aleksandra0KR.controller.CatController;
import ru.aleksandra0KR.controller.PersonController;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.dto.PersonDto;
import ru.aleksandra0KR.hibernate.HibernateSessionFactoryUtil;

public class Main {

  public static void main(String[] args) {

    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

    PersonController personController = new PersonController();
    CatController catController = new CatController();

    PersonDto personLena = personController.createPerson("Lena", LocalDate.of(1878, 8, 17));
    CatDto catLily = catController.createCat("Lily", LocalDate.of(2012, 10, 5), "Black",
        "Devan Rex", personLena);

    PersonDto personSasha = personController.createPerson("Sasha", LocalDate.of(2004, 5, 9));
    CatDto catFufa = catController.createCat("Fufa", LocalDate.of(2020, 1, 31),
        "Сhinchilla on silver", "Scottish longhair", personSasha);

    catController.addFriend(catLily.getId(), catFufa.getId());

    catController.getAllFriends(catLily.getId());


    personController.deletePerson(personLena);
    personController.deletePerson(personSasha);

    session.close();
  }

}