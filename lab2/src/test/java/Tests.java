import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.aleksandra0KR.dao.CatDao;
import ru.aleksandra0KR.dao.CatDaoPostgres;
import ru.aleksandra0KR.dao.PersonDao;
import ru.aleksandra0KR.dao.PersonPostgresDao;
import ru.aleksandra0KR.entity.Cat;
import ru.aleksandra0KR.entity.Person;

public class Tests {

  @Test
  void TestCreatePerson() {
    PersonDao personDaoMock = Mockito.mock(PersonPostgresDao.class);

    Person person = new Person("Dima", LocalDate.of(2004, 1, 1));
    person.setPerson_id(1);

    Mockito.when(personDaoMock.addPerson(person)).thenReturn(person.getPerson_id());
    assertEquals(personDaoMock.addPerson(person), 1);
  }

  @Test
  void TestCreateCat() {

    CatDao catDaoMock = Mockito.mock(CatDaoPostgres.class);

    Cat cat = new Cat("Lucky", "british", "Grey", LocalDate.now(),
        new Person("Nasty", LocalDate.of(2004, 3, 21)));
    cat.setId(1);

    Mockito.when(catDaoMock.addCat(cat)).thenReturn(cat.getId());
    assertEquals(catDaoMock.addCat(cat), 1);
  }

  @Test
  void TestAddFriend() {

    CatDao catDaoMock = Mockito.mock(CatDao.class);

    Cat catFuFa = new Cat("FuFa", "Сhinchilla on silver", "Scottish longhair",
        LocalDate.of(2020, 1, 31),
        new Person("Sasha", LocalDate.now()));
    Cat catLucky = new Cat("Lucky", "Grey", "British", LocalDate.of(2021, 6, 15),
        new Person("Nasty", LocalDate.now()));
    catFuFa.setId(1);
    catLucky.setId(2);

    Mockito.when(catDaoMock.addCat(catFuFa)).thenReturn(catFuFa.getId());
    Mockito.when(catDaoMock.addCat(catLucky)).thenReturn(catLucky.getId());
    Mockito.when(catDaoMock.findAllFriends(catLucky)).thenReturn(Arrays.asList(catFuFa));

    catDaoMock.addCat(catFuFa);
    catDaoMock.addCat(catLucky);
    catDaoMock.addFriend(catLucky, catFuFa);

    List<Cat> friends = catDaoMock.findAllFriends(catLucky);

    assertEquals(Arrays.asList(catFuFa), friends);
  }

  @Test
  void TestUpdateCat() {
    CatDao catDAO = Mockito.mock(CatDaoPostgres.class);

    Cat cat = new Cat("Lucky", "british", "Grey", LocalDate.now(),
        new Person("Nasty", LocalDate.of(2004, 3, 21)));
    cat.setId(1);

    Mockito.when(catDAO.addCat(cat)).thenReturn(cat.getId());
    Mockito.when(catDAO.findCatByID(1)).thenReturn(cat);
    catDAO.addCat(cat);
    cat.setColor("Grey and White");
    catDAO.updateCat(cat);
    assertEquals(catDAO.findCatByID(1).getColor(), cat.getColor());
  }
}
