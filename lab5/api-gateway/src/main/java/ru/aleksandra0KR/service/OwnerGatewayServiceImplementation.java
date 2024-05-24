package ru.aleksandra0KR.service;

import java.security.Principal;
import java.util.List;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.aleksandra0KR.CatClient;
import ru.aleksandra0KR.OwnerClient;
import ru.aleksandra0KR.controller.CatPost;
import ru.aleksandra0KR.controller.OwnerPost;
import ru.aleksandra0KR.dao.Person;
import ru.aleksandra0KR.dto.CatDtoClient;
import ru.aleksandra0KR.dto.CatDtoGateway;
import ru.aleksandra0KR.dto.OwnerDtoClient;
import ru.aleksandra0KR.exception.CatsPrivateInformationException;
import ru.aleksandra0KR.exception.OwnerPrivateInformation;
import ru.aleksandra0KR.exception.PersonDoesntExistException;
import ru.aleksandra0KR.ru.dto.CatDtoMessage;
import ru.aleksandra0KR.ru.dto.OwnerDtoMessage;

@Service
public class OwnerGatewayServiceImplementation implements OwnerGatewayService {

  private final RabbitTemplate rabbitTemplate;
  private final OwnerClient ownerClient;
  private final PersonService personService;


  public OwnerGatewayServiceImplementation(RabbitTemplate rabbitTemplate, OwnerClient ownerClient, PersonService personService) {
    this.rabbitTemplate = rabbitTemplate;
    this.ownerClient = ownerClient;
    this.personService = personService;
  }

  public Person Person(Principal principal) {
    Person person = personService.getPersonByName(principal.getName());
    if (person != null) {
      return person;
    }
    throw new PersonDoesntExistException(principal.getName());
  }
  @Override
  public OwnerDtoClient findPersonById( Long id, Principal principal) {
    Person person = Person(principal);
    if (!person.getOwnerId().equals(id)) {
      throw new OwnerPrivateInformation(principal.getName());
    }
    return ownerClient.GetOwnerById(id);
  }

  @Override
  public OwnerDtoClient findPersonByName(String name, Principal principal) {
    Person person = Person(principal);
    OwnerDtoClient owner = ownerClient.GetOwnerByName(name);
    if (!person.getOwnerId().equals(owner.getOwner_id())) {
      throw new OwnerPrivateInformation(principal.getName());
    }
    return owner;
  }

  @Override
  public void update(OwnerPost ownerPost, Principal principal) {
    Person person = Person(principal);
    if (person.getOwnerId() != ownerPost.getId()) {
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
    Person person = Person( principal);
    if (id != person.getOwnerId()) {
      throw new OwnerPrivateInformation(principal.getName());
    }
    rabbitTemplate.convertAndSend("ownerDeleteQueue", id);

  }

  @Override
  public void addOwner(OwnerPost ownerPost){
    OwnerDtoMessage ownerDtoMessage = OwnerDtoMessage.builder()
        .id(ownerPost.getId())
        .name(ownerPost.getName())
        .birthDay(ownerPost.getBirthDay())
        .build();

    rabbitTemplate.convertAndSend("ownerAddQueue", ownerDtoMessage);

  }
}
