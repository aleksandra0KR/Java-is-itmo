package ru.aleksandra0KR.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.aleksandra0KR.dao.Cat;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.dto.CatDtoClient;
import ru.aleksandra0KR.exception.CatDoesntExistsException;
import ru.aleksandra0KR.exception.CatsPrivateInformationException;
import ru.aleksandra0KR.mapper.CatMapper;
import ru.aleksandra0KR.repository.CatRepository;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.transaction.annotation.Transactional;
import ru.aleksandra0KR.ru.dto.CatDtoMessage;
import ru.aleksandra0KR.ru.dto.CatFriendDtoMessage;

@Component
@EnableRabbit
public class CatServiceImplementation implements CatService {

  final
  CatRepository catRepository;

  @RabbitListener(queues = "catAddQueue")
  @Transactional
  public void addCat(CatDtoMessage cat) {
    Cat dao = catRepository.save(
Cat.builder()
    .name(cat.getName())
    .ownerId(cat.getOwnerID())
    .birthday(cat.getBirthDay())
    .breed(cat.getBreed())
    .color(cat.getCatColor())
    .build()
    );
    Long generatedId = dao.getId();
    cat.setId(generatedId);
  }

  public CatServiceImplementation(CatRepository catRepository) {
    this.catRepository = catRepository;
  }


  public CatDtoClient findCatByID(long id) {
    Cat cat = catRepository.findById(id)
        .orElseThrow(() -> new CatDoesntExistsException(id));
    System.out.println(cat);
    return  CatDtoClient.builder()
        .id(cat.getId())
        .name(cat.getName())
        .owner(cat.getOwnerId())
        .birthDate(cat.getBirthday())
        .breed(cat.getBreed())
        .color(cat.getColor())
        .build();
  }

  @Override
  public List<CatDtoClient> findCatsByColorOrBreedOrName(String color, String breed,
      String name) {
    if (color == null && breed == null && name == null) {
      throw new CatDoesntExistsException("");
    }
    if (breed != null) {
      return catRepository.findCatByBreed(breed).stream()
          .map(CatMapper::asDto)
          .collect(Collectors.toList());
    } else if (color != null) {
      return catRepository.findCatByColor(color).stream()
          .map(CatMapper::asDto)
          .collect(Collectors.toList());
    } else {
      return catRepository.findCatByName(name).stream()
          .map(CatMapper::asDto)
          .collect(Collectors.toList());
    }
  }

  @RabbitListener(queues = "catUpdateQueue")
  @Transactional
  public void updateCat(CatDtoMessage cat) {
    Cat catFromRepo = catRepository.findById(cat.getId())
        .orElseThrow(() -> new CatDoesntExistsException(cat.getId()));

    catFromRepo.setColor(cat.getCatColor());
    catFromRepo.setName(cat.getName());
    catFromRepo.setBreed(cat.getBreed());
    catFromRepo.setBirthday(cat.getBirthDay());

    catRepository.save(catFromRepo);
  }

  @RabbitListener(queues = "catDeleteQueue")
  @Transactional
  @Override
  public void deleteCat(long id) {
   Cat catFromRepo = catRepository.findById(id)
        .orElseThrow(() -> new CatDoesntExistsException(id));

   catRepository.delete(catFromRepo);

  }

  public List<CatDtoClient> getAllFriends(long id) {
    var res = catRepository.getFriendsById(id)
        .stream()
        .map(CatMapper::asDto)
        .toList();

    return res;
  }

  @RabbitListener(queues = "catAddFriendQueue")
  @Transactional
  public void addFriend(CatFriendDtoMessage catFriendDtoMessage) {
    Cat cat = catRepository.findById(catFriendDtoMessage.getCatId())
        .orElseThrow(() -> new CatDoesntExistsException(catFriendDtoMessage.getCatId()));
    Cat friend = catRepository.findById(catFriendDtoMessage.getFriendId())
        .orElseThrow(() -> new CatDoesntExistsException(catFriendDtoMessage.getFriendId()));

    cat.addFriend(friend);
    catRepository.save(cat);
  }
}









