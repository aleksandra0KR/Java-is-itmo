package ru.aleksandra0KR.service;

import java.time.LocalDate;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.aleksandra0KR.CatClient;
import ru.aleksandra0KR.OwnerClient;
import ru.aleksandra0KR.dto.CatDtoClient;
import ru.aleksandra0KR.dto.CatDtoGateway;
import ru.aleksandra0KR.ru.dto.CatDtoMessage;

@Service
@RequiredArgsConstructor
public class CatGatewayServiceImplementation implements CatGatewayService {

  private final RabbitTemplate rabbitTemplate;
  private final CatClient catClient;
  private final OwnerClient ownerClient;


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
  public CatDtoGateway getById(Long uuid) {
    CatDtoClient byId = catClient.findCatByID(uuid);
    System.out.println(byId);
   /* OwnerDto self = byId.getOwner() != null
        ? ownerClient.getSelf()
        : null;*/

    return CatDtoGateway.builder()
        .id(byId.getId())
        .name(byId.getName())
        .birthDate(byId.getBirthDate())
        .breed(byId.getBreed())
        .color(byId.getColor())
        .friends(byId.getFriends())
        .owner(null)
        .build();
  }


  @Override
  public void addFriend(long catId, long friendId, long ownerId) {

  }


}
