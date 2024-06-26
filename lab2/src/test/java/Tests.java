
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import javax.transaction.Transaction;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import ru.aleksandra0KR.dao.CatDaoPostgres;
import ru.aleksandra0KR.dao.PersonPostgresDao;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.dto.PersonDto;
import ru.aleksandra0KR.entity.Cat;
import ru.aleksandra0KR.entity.Person;
import ru.aleksandra0KR.mapper.CatMapper;
import ru.aleksandra0KR.mapper.PersonMapper;
import ru.aleksandra0KR.service.CatServiceImplementation;
import ru.aleksandra0KR.service.PersonServiceImplementation;

public class Tests {

  private CatDaoPostgres catRepository;
  private PersonPostgresDao personRepository;

  private CatServiceImplementation catService;
  private PersonServiceImplementation personService;

  @BeforeEach
  public void setup() {
    catRepository = Mockito.mock(CatDaoPostgres.class);
    personRepository = Mockito.mock(PersonPostgresDao.class);

    catService = Mockito.mock(CatServiceImplementation.class);
    personService =  Mockito.mock(PersonServiceImplementation.class);
  }

  @Test
  public void CreateCat() {

    Person owner = new Person(
        1,
        "Sasha",
        LocalDate.of(2004, 5, 9),
        new ArrayList<>());

    Cat cat = new Cat(
        "Fufa",
        "perfect",
        "pink",
        LocalDate.of(2020, 1, 31),
        owner);

    CatDto catDto = CatMapper.asDto(cat);
    catDto.setId(1);

    when(catRepository.addCat(ArgumentMatchers.any(Cat.class), ArgumentMatchers.any(Session.class)))
        .thenAnswer((Answer<Long>) invocation -> {
          Cat cat1 = invocation.getArgument(0);
          return cat1.getId();
        });

    when(catService.addCat(cat.getName(), cat.getBirthday(), cat.getColor(),
        cat.getBreed(), PersonMapper.asDto(owner))).thenReturn(CatMapper.asDto(cat));
    CatDto catDto1 = catService.addCat(cat.getName(), cat.getBirthday(), cat.getColor(),
        cat.getBreed(), PersonMapper.asDto(owner));

    Assertions.assertEquals(
        catDto1.getName(),
        cat.getName());
  }

  @Test
  public void FindCatById() {

    Person owner = new Person(
        1,
        "Sasha",
        LocalDate.of(2004, 5, 9),
        new ArrayList<>());

    Cat cat = new Cat(
        "Fufa",
        "perfect",
        "pink",
        LocalDate.of(2020, 1, 31),
        owner);

    CatDto catDto = CatMapper.asDto(cat);
    catDto.setId(1);

    when(catRepository.findCatByID(ArgumentMatchers.anyLong(), ArgumentMatchers.any(Session.class)))
        .thenAnswer((Answer<Cat>) invocation -> cat);

    when(catService.findCatByID(1)).thenReturn(CatMapper.asDto(cat));
    CatDto catDto1 = catService.findCatByID(1);

    Assertions.assertEquals(
        catDto1.getName(),
        cat.getName());
  }

  @Test
  public void addOwner() {

    Person owner = new Person(
        1,
        "Sasha",
        LocalDate.of(2004, 5, 9),
        new ArrayList<>());

    PersonDto person = PersonMapper.asDto(owner);
    person.setId(1);

    when(personRepository.addPerson(ArgumentMatchers.any(Person.class), ArgumentMatchers.any(Session.class)))
        .thenAnswer((Answer<Long>) invocation -> {
          Person owner1 = invocation.getArgument(0);
          return owner1.getPerson_id();
        });

    when(personService.addPerson(owner.getName(), owner.getBirthdate())).thenReturn(person);

    PersonDto ownerDto1 = personService.addPerson(owner.getName(), owner.getBirthdate());

    Assertions.assertEquals(
        ownerDto1.getBirthDate(),
        owner.getBirthdate());
  }

  @Test
  public void createCat_addNonexistentFriend_GetException() {
    Person owner = new Person(
        1,
        "Sasha",
        LocalDate.of(2004, 5, 9),
        new ArrayList<>());

    Cat cat = new Cat(
        "Fufa",
        "perfect",
        "pink",
        LocalDate.of(2020, 1, 31),
        owner);

    when(catRepository.addCat(ArgumentMatchers.any(Cat.class), ArgumentMatchers.any(Session.class)))
        .thenAnswer((Answer<Long>) invocation -> {
          Cat cat1 = invocation.getArgument(0);
          return cat1.getId();
        });

    when(personRepository.addPerson(ArgumentMatchers.any(Person.class), ArgumentMatchers.any(Session.class)))
        .thenAnswer((Answer<Long>) invocation -> {
          Person owner1 = invocation.getArgument(0);
          return owner1.getPerson_id();
        });

    when(personRepository.findPersonByID(Mockito.anyInt(), ArgumentMatchers.any(Session.class))).thenReturn(owner);
    when(catRepository.findCatByID(1, null)).thenReturn(cat);
    Mockito.doNothing().when(catRepository)
        .addFriend(ArgumentMatchers.any(Cat.class), ArgumentMatchers.any(Cat.class), ArgumentMatchers.any(Session.class));

    PersonDto ownerDto1 = personService.addPerson(owner.getName(), owner.getBirthdate());
    doThrow(new NullPointerException()).when(catService).addFriend(1L, 5L);

    CatDto catDto1 = catService.addCat(cat.getName(), cat.getBirthday(), cat.getColor(),
        cat.getBreed(), PersonMapper.asDto(owner));

    assertThrows(NullPointerException.class,
        () -> catService.addFriend(1, 5));
  }

}
