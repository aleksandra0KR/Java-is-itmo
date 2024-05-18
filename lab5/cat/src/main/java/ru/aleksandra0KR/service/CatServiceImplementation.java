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

@Component
@EnableRabbit
public class CatServiceImplementation implements CatService {

  final
  CatRepository catRepository;

  @RabbitListener(queues = "catAddQueue")
  @Transactional
  public void addCat(CatDto cat) {
    Cat dao = catRepository.save(CatMapper.asDao(cat));
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
  public List<CatDto> findCatsByColorOrBreedOrName(String color, String breed,
      String name, long id) {
    if (color == null && breed == null && name == null) {
      throw new CatDoesntExistsException("");
    }
    if (breed != null) {
      return catRepository.findCatByBreed(breed).stream()
          .filter(cat -> cat.getOwnerId() != null && Objects.equals(cat.getOwnerId(), id))
          .map(CatMapper::asDto)
          .collect(Collectors.toList());
    } else if (color != null) {
      return catRepository.findCatByColor(color).stream()
          .filter(cat -> cat.getOwnerId() != null && Objects.equals(cat.getOwnerId(), id))
          .map(CatMapper::asDto)
          .collect(Collectors.toList());
    } else {
      return catRepository.findCatByName(name).stream()
          .filter(cat -> cat.getOwnerId() != null && Objects.equals(cat.getOwnerId(), id))
          .map(CatMapper::asDto)
          .collect(Collectors.toList());
    }
  }

  @RabbitListener(queues = "catUpdateQueue")
  @Transactional
  public void updateCat(CatDto cat, long id) {
    Cat catFromRepo = catRepository.findById(cat.getId())
        .orElseThrow(() -> new CatDoesntExistsException(cat.getId()));
    if (catFromRepo.getOwnerId() != null && !Objects.equals(catFromRepo.getOwnerId(),
        id)) {
      throw new CatsPrivateInformationException();
    }
    catFromRepo.setColor(cat.getColor());
    catFromRepo.setName(cat.getName());
    catFromRepo.setBreed(cat.getBreed());
    catFromRepo.setBirthday(cat.getBirthDate());

    catRepository.save(catFromRepo);
  }

  @RabbitListener(queues = "catAddFriendQueue")
  @Transactional
  public void addFriend(long catID, long friendID) {
    Cat cat = catRepository.findById(catID)
        .orElseThrow(() -> new CatDoesntExistsException(catID));
    Cat friend = catRepository.findById(friendID)
        .orElseThrow(() -> new CatDoesntExistsException(friendID));

    cat.addFriend(friend);
    catRepository.save(cat);
  }

  @RabbitListener(queues = "catPersonQueue")
  @Transactional
  @Override
  public void attachPerson(Long catId, Long personId) {
    Cat cat = catRepository.findById(catId)
        .orElseThrow(() -> new CatDoesntExistsException(catId));

    cat.setOwnerId(personId);
    catRepository.save(cat);
  }

  @RabbitListener(queues = "catPersonQueue")
  @Transactional
  @Override
  public void detachPerson(Long catId, Long personId) {

    Cat cat = catRepository.findById(catId)
        .orElseThrow(() -> new CatDoesntExistsException(catId));
    cat.setOwnerId(null);

    catRepository.save(cat);
  }

  public List<CatDto> getAllFriends(long id) {
    return catRepository.getFriendsById(id)
        .stream()
        .map(CatMapper::asDto)
        .collect(Collectors.toList());
  }

  @RabbitListener(queues = "catDeleteQueue")
  @Transactional
  public void deleteCat(long id, long personId) {

    Cat cat = catRepository.findById(id)
        .orElseThrow(() -> new CatDoesntExistsException(id));

    if (cat.getOwnerId() != null && !Objects.equals(personId, cat.getOwnerId())) {
      throw new CatsPrivateInformationException();
    }

    catRepository.delete(cat);
  }
}









