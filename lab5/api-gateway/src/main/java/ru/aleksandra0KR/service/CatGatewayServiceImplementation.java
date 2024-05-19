package ru.aleksandra0KR.service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.aleksandra0KR.CatClient;
import ru.aleksandra0KR.OwnerClient;
import ru.aleksandra0KR.dao.Person;
import ru.aleksandra0KR.dto.CatDtoClient;
import ru.aleksandra0KR.dto.CatDtoGateway;
import ru.aleksandra0KR.exception.CatsPrivateInformationException;
import ru.aleksandra0KR.exception.PersonDoesntExistException;
import ru.aleksandra0KR.ru.dto.CatDtoMessage;

@Service
@RequiredArgsConstructor
public class CatGatewayServiceImplementation implements CatGatewayService {

  private final RabbitTemplate rabbitTemplate;
  private final CatClient catClient;
  private final OwnerClient ownerClient;
  private final PersonService personService;

  public Long OwnerId(Principal principal) {
    Person person = personService.getPersonByName(principal.getName());
    if (person != null) {
      return  person.getOwnerId();
    }
    throw new PersonDoesntExistException(principal.getName());
  }

  @Override
  public void create(String name, long ownerId, LocalDate birthdat, String breed, String color) {
    CatDtoMessage catDtoMessage = CatDtoMessage.builder()
        .id(new Random().nextLong())
        .name(name)
        .ownerID(ownerId)
        .birthDay(birthdat)
        .breed(breed)
        .catColor(color)
        .build();

    rabbitTemplate.convertAndSend("catAddQueue", catDtoMessage);
  }

  @Override
  public CatDtoGateway getById(Long uuid, Principal principal) {
    CatDtoClient cat = catClient.findCatByID(uuid);
    Long ownerId = OwnerId(principal);

    //OwnerD self = byId.getOwner() != null
      //  ? ownerClient.getSelf()
        //: null;*/
    if (ownerId != cat.getOwner()){
      throw new CatsPrivateInformationException();
    }

    return CatDtoGateway.builder()
        .id(cat.getId())
        .name(cat.getName())
        .birthDate(cat.getBirthDate())
        .breed(cat.getBreed())
        .color(cat.getColor())
        .friends(cat.getFriends())
        .owner(null)
        .build();
  }


  @Override
  public void addFriend(long catId, long friendId, long ownerId) {

  }


}
