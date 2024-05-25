package ru.aleksandra0KR.service;

import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.aleksandra0KR.CatClient;
import ru.aleksandra0KR.OwnerClient;
import ru.aleksandra0KR.dto.CatPost;
import ru.aleksandra0KR.dao.Person;
import ru.aleksandra0KR.dto.CatDtoClient;
import ru.aleksandra0KR.dto.CatDtoGateway;
import ru.aleksandra0KR.dto.OwnerDtoClient;
import ru.aleksandra0KR.ru.dto.PersonDtoMessage;
import ru.aleksandra0KR.ru.exception.CatDoesntExistsException;
import ru.aleksandra0KR.ru.exception.CatsPrivateInformationException;
import ru.aleksandra0KR.ru.exception.PersonDoesntExistException;
import ru.aleksandra0KR.ru.dto.CatDtoMessage;
import ru.aleksandra0KR.ru.dto.CatFriendDtoMessage;

@Service
@RequiredArgsConstructor
public class CatGatewayServiceImplementation implements CatGatewayService {

  private final RabbitTemplate rabbitTemplate;
  private final CatClient catClient;
  private final OwnerClient ownerClient;
  private final PersonService personService;

  public Person Person(Principal principal) {
    Person person = personService.getPersonByName(principal.getName());
    if (person != null) {
      return person;
    }
    throw new PersonDoesntExistException(principal.getName());
  }


  @Override
  public void deleteCat(Principal principal, long id) {
    CatDtoGateway catClient = getById(id, principal);
    if (catClient == null) {
      throw new CatDoesntExistsException(id);
    }
    rabbitTemplate.convertAndSend("catDeleteQueue", id);

  }

  @Override
  public void create(CatPost catPost) {
    CatDtoMessage catDtoMessage = CatDtoMessage.builder()
        .name(catPost.getName())
        .ownerID(catPost.getOwnerID())
        .birthDay(catPost.getBirthDay())
        .breed(catPost.getBreed())
        .catColor(catPost.getColor())
        .build();

    rabbitTemplate.convertAndSend("catAddQueue", catDtoMessage);
  }

  @Override
  public void update(CatPost catPost, Principal principal) {
    Person person = Person(principal);
    if (!person.getOwnerId().equals(catPost.getOwnerID()) && !person.getRoles().equals("ROLE_ADMIN")) {
      throw new CatsPrivateInformationException();
    }
    CatDtoMessage catDtoMessage = CatDtoMessage.builder()
        .id(catPost.getId())
        .name(catPost.getName())
        .ownerID(catPost.getOwnerID())
        .birthDay(catPost.getBirthDay())
        .breed(catPost.getBreed())
        .catColor(catPost.getColor())
        .build();

    rabbitTemplate.convertAndSend("catUpdateQueue", catDtoMessage);
  }

  @Override
  public CatDtoGateway getById(Long uuid, Principal principal) {
    CatDtoClient cat = catClient.findCatByID(uuid);
    Person person = Person(principal);
    OwnerDtoClient ownerDtoClient = ownerClient.GetOwnerById(person.getOwnerId());
    if (!person.getOwnerId().equals(cat.getOwner()) && !person.getRoles().equals("ROLE_ADMIN")) {
      throw new CatsPrivateInformationException();
    }

    return CatDtoGateway.builder()
        .id(cat.getId())
        .name(cat.getName())
        .birthDate(cat.getBirthDate())
        .breed(cat.getBreed())
        .color(cat.getColor())
        .owner(ownerDtoClient)
        .build();
  }

  @Override
  public void addFriend(CatFriendDtoMessage catFriendDtoMessage, Principal principal) {
    Person person = Person(principal);
    CatDtoClient catDtoClient = catClient.findCatByID(catFriendDtoMessage.getCatId());
    CatDtoClient catFriendDtoClient = catClient.findCatByID(catFriendDtoMessage.getFriendId());
    if (!person.getOwnerId().equals(catFriendDtoClient.getOwner()) && !person.getOwnerId().equals(catDtoClient.getOwner()) && !person.getRoles().equals("ROLE_ADMIN")) {
      throw new CatsPrivateInformationException();
    }
    rabbitTemplate.convertAndSend("catAddFriendQueue", catFriendDtoMessage);
  }


  @Override
  public List<CatDtoClient> getCatsByColorOrBreedOrName(Principal principal, String color,
      String breed, String name) {
    Person person = personService.getPersonByName(principal.getName());
    PersonDtoMessage personDtoMessage = new PersonDtoMessage(person.getPerson_id(), person.getUsername(), person.getOwnerId(), person.getRoles());

    return catClient.findCatsByColorOrBreedOrName(color, breed, name, personDtoMessage);
  }

  @Override
  public List<CatDtoClient> getFriendsById(Principal principal, long catId) {
    Person person = personService.getPersonByName(principal.getName());
    CatDtoClient cat = catClient.findCatByID(catId);
    if (!cat.getOwner().equals(person.getOwnerId())) {
      throw new CatsPrivateInformationException();
    }
    return catClient.getAllFriends(catId);
  }

  @Override
  public List<CatDtoClient> getAllOwnerCats(Principal principal, long id) {
    Person person = personService.getPersonByName(principal.getName());
    if (id != person.getOwnerId()) {
      throw new CatsPrivateInformationException();
    }
    PersonDtoMessage personDtoMessage = new PersonDtoMessage(person.getPerson_id(), person.getUsername(), person.getOwnerId(), person.getRoles());

    return catClient.getAllOwnerCats(personDtoMessage);
  }


}