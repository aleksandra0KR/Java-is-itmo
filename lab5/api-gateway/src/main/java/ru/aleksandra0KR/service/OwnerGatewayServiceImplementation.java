package ru.aleksandra0KR.service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.aleksandra0KR.OwnerClient;
import ru.aleksandra0KR.dto.OwnerPost;
import ru.aleksandra0KR.dao.Person;
import ru.aleksandra0KR.dto.OwnerDtoClient;
import ru.aleksandra0KR.ru.exception.OwnerPrivateInformation;
import ru.aleksandra0KR.ru.exception.PersonDoesntExistException;
import ru.aleksandra0KR.ru.exception.PersonPrivilegeException;
import ru.aleksandra0KR.ru.dto.OwnerDtoMessage;

@Service
@RequiredArgsConstructor
public class OwnerGatewayServiceImplementation implements OwnerGatewayService {

  private final RabbitTemplate rabbitTemplate;
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
  public OwnerDtoClient findPersonById(Long id, Principal principal) {
    Person person = Person(principal);
    OwnerDtoClient ownerDtoClient =  ownerClient.GetOwnerById(id);
    if (!person.getPerson_id().equals(ownerDtoClient.getPerson_id()) && !person.getRoles().equals("ROLE_ADMIN")) {
      throw new OwnerPrivateInformation(principal.getName());
    }
    return ownerDtoClient;
  }

  @Override
  public OwnerDtoClient findPersonByName(String name, Principal principal) {
    Person person = Person(principal);
    OwnerDtoClient owner = ownerClient.GetOwnerByName(name);
    System.out.println(owner);
    if (!person.getPerson_id().equals(owner.getPerson_id()) && !person.getRoles().equals("ROLE_ADMIN")) {
      throw new OwnerPrivateInformation(owner.getName());
    }
    return owner;
  }

  @Override
  public void update(OwnerPost ownerPost, Principal principal) {
    Person person = Person(principal);
    OwnerDtoClient ownerDtoClient = ownerClient.GetOwnerById(ownerPost.getId());

    if (!person.getPerson_id().equals(ownerDtoClient.getPerson_id()) && !person.getRoles().equals("ROLE_ADMIN")) {
      throw new OwnerPrivateInformation(ownerPost.getName());
    }
    OwnerDtoMessage ownerDtoMessage = OwnerDtoMessage.builder()
        .id(ownerPost.getId())
        .name(ownerPost.getName())
        .birthDay(ownerPost.getBirthDay())
        .build();

    rabbitTemplate.convertAndSend("ownerUpdateQueue", ownerDtoMessage);
  }

  @Override
  public void deleteOwner(Principal principal, long id) {
    Person person = Person(principal);
    OwnerDtoClient ownerDtoClient = ownerClient.GetOwnerById(id);
    if (!ownerDtoClient.getPerson_id().equals( person.getPerson_id()) && !person.getRoles().equals("ROLE_ADMIN")) {
      throw new OwnerPrivateInformation(principal.getName());
    }
    rabbitTemplate.convertAndSend("ownerDeleteQueue", id);

  }

  @Override
  public void addOwner(String name, LocalDate birthday, Principal principal) {
    Person person = Person(principal);
    if (!Objects.equals(person.getRoles(), "ROLE_ADMIN")) {
      throw new PersonPrivilegeException();
    }

    OwnerDtoMessage ownerDtoMessage = OwnerDtoMessage.builder()
        .name(name)
        .birthDay(birthday)
        .person_id(person.getPerson_id())
        .build();

    rabbitTemplate.convertAndSend("ownerAddQueue", ownerDtoMessage);

    OwnerDtoClient ownerDtoClient = ownerClient.GetOwnerByName(name);
    person.setOwnerId(ownerDtoClient.getOwner_id());

  }
}
