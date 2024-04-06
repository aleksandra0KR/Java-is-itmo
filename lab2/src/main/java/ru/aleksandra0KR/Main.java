package ru.aleksandra0KR;

import java.time.LocalDate;
import ru.aleksandra0KR.dao.PersonPostgresDao;

import ru.aleksandra0KR.service.PersonService;
import ru.aleksandra0KR.service.PersonServiceImplementation;

public class Main {

  public static void main(String[] args) {

    PersonPostgresDao personPostgresDao = new PersonPostgresDao();
    PersonService personDto = new PersonServiceImplementation(personPostgresDao);
    personDto.addPerson("dly", LocalDate.of(1878, 8, 17));
    personDto.addPerson("dly", LocalDate.of(1878, 8, 17));
  }
}
