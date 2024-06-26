package ru.aleksandra0KR.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.entity.Cat;
import ru.aleksandra0KR.entity.Person;
import ru.aleksandra0KR.exceptions.CatDoesntExistsException;
import ru.aleksandra0KR.exceptions.PersonDoesntExistException;
import ru.aleksandra0KR.mapper.CatMapper;
import ru.aleksandra0KR.repository.CatRepository;
import ru.aleksandra0KR.repository.PersonRepository;

@Service
public class CatServiceImplementation implements CatService {

  final
  CatRepository catRepository;

  final
  PersonRepository personRepository;

  public CatServiceImplementation(CatRepository catRepository, PersonRepository personRepository) {
    this.catRepository = catRepository;
    this.personRepository = personRepository;
  }


  public CatDto findCatByID(long id) {
    Cat cat = catRepository.findById(id)
        .orElseThrow(() -> new CatDoesntExistsException(id));
    return CatMapper.asDto(cat);
  }

  @Override
  public List<CatDto> findCatsByColor(String color) {
    return catRepository.findCatByColor(color)
        .stream()
        .map(CatMapper::asDto)
        .collect(Collectors.toList());
  }
  @Override
  public List<CatDto> findCatsByColor(String color, String breed, String name){
    if (color == null && breed == null && name == null) {
      throw new CatDoesntExistsException("");
    }

    if (breed != null) {
      return catRepository.findCatByBreed(breed).stream()
          .map(CatMapper::asDto)
          .collect(Collectors.toList());
    }
    else if (color != null) {
      return catRepository.findCatByColor(color).stream()
          .map(CatMapper::asDto)
          .collect(Collectors.toList());
    }
    else if (name != null) {
      return catRepository.findCatByName(name).stream()
          .map(CatMapper::asDto)
          .collect(Collectors.toList());
    }
   return null;
  }

  @Override
  public List<CatDto> findCatByBreed(String breed) {
    return catRepository.findCatByBreed(breed)
        .stream()
        .map(CatMapper::asDto)
        .collect(Collectors.toList());
  }

  @Override
  public List<CatDto> findCatByName(String name) {
    List<CatDto> cats = catRepository.findCatByName(name)
        .stream()
        .map(CatMapper::asDto)
        .collect(Collectors.toList());
    if(cats.isEmpty()) throw new CatDoesntExistsException(name);
    return cats;

  }

  public CatDto addCat(CatDto cat) {
    Cat dao = catRepository.save(CatMapper.asDao(cat));
    Long generatedId = dao.getId();
    cat.setId(generatedId);
    return cat;
  }

  @Transactional
  public void updateCat(CatDto cat) {
    Cat catFromRepo = catRepository.findById(cat.getId()).orElseThrow(() -> new CatDoesntExistsException(cat.getId()));

    catFromRepo.setColor(cat.getColor());
    catFromRepo.setName(cat.getName());
    catFromRepo.setBreed(cat.getBreed());
    catFromRepo.setBirthday(cat.getBirthDate());

    catRepository.save(catFromRepo);
  }

  @Transactional
  public void addFriend(long catID, long friendID) {
    Cat cat = catRepository.findById(catID)
        .orElseThrow(() -> new CatDoesntExistsException(catID));
    Cat friend = catRepository.findById(friendID)
        .orElseThrow(() -> new CatDoesntExistsException(friendID));

    cat.addFriend(friend);
    catRepository.save(cat);
  }

  @Transactional
  @Override
  public void attachPerson(Long personId, Long catId) {
    Person person = personRepository.findById(personId)
        .orElseThrow(() -> new PersonDoesntExistException(personId));
    Cat cat = catRepository.findById(catId)
        .orElseThrow(() -> new CatDoesntExistsException(catId));

    cat.setPerson(person);
    var cats = person.getCats();
    cats.add(cat);
    person.setCats(cats);
    catRepository.save(cat);
    personRepository.save(person);
  }

  @Transactional
  @Override
  public void detachPerson(Long personId, Long catId) {
    Person person = personRepository.findById(personId)
        .orElseThrow(() -> new PersonDoesntExistException(personId));
    Cat cat = catRepository.findById(catId)
        .orElseThrow(() -> new CatDoesntExistsException(catId));

    cat.setPerson(null);
    var cats = person.getCats();
    cats.remove(cat);
    person.setCats(cats);
    catRepository.save(cat);
    personRepository.save(person);
  }

  public List<CatDto> getAllFriends(long id) {
    return catRepository.getFriendsById(id)
        .stream()
        .map(CatMapper::asDto)
        .collect(Collectors.toList());
  }

  @Transactional
  public void deleteCat(long id) {
    Cat cat = catRepository.findById(id)
        .orElseThrow(() -> new CatDoesntExistsException(id));
    catRepository.delete(cat);
  }
}






