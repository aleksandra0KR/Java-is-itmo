package ru.aleksandra0KR.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.entity.Cat;
import ru.aleksandra0KR.entity.Person;
import ru.aleksandra0KR.mapper.CatMapper;
import ru.aleksandra0KR.repository.CatRepository;
import ru.aleksandra0KR.repository.PersonRepository;

@Service
@ComponentScan(basePackages = {"ru.aleksandra0KR.repository"})
public class CatServiceImplementation implements CatService {

  @Autowired
  CatRepository catRepository;

  @Autowired
  PersonRepository personRepository;


  public CatDto findCatByID(long id) {
    Cat cat = catRepository.findById(id)
        .orElseThrow(() -> new NullPointerException("there is no cat with id " + id));
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
  public List<CatDto> findCatByBreed(String breed) {
    return catRepository.findCatByBreed(breed)
        .stream()
        .map(CatMapper::asDto)
        .collect(Collectors.toList());
  }

  @Override
  public List<CatDto> findCatByName(String name) {
    return catRepository.findCatByName(name)
        .stream()
        .map(CatMapper::asDto)
        .collect(Collectors.toList());
  }

  public CatDto addCat(CatDto cat) {
    Cat dao = catRepository.save(CatMapper.asDao(cat));
    Long generatedId = dao.getId();
    cat.setId(generatedId);
    return cat;
  }

  @Transactional
  public void updateCat(CatDto cat) {
    Cat catFromRepo = catRepository.getById(cat.getId());

    catFromRepo.setColor(cat.getColor());
    catFromRepo.setName(cat.getName());
    catFromRepo.setBreed(cat.getBreed());
    catFromRepo.setBirthday(cat.getBirthDate());

    catRepository.save(catFromRepo);
  }

  @Transactional
  public void addFriend(long catID, long friendID) {
    Cat cat = catRepository.findById(catID)
        .orElseThrow(() -> new NullPointerException("there is no cat with id " + catID));
    Cat friend = catRepository.findById(friendID)
        .orElseThrow(() -> new NullPointerException("there is no friend with id " + friendID));

    cat.addFriend(friend);
    catRepository.save(cat);
  }

  @Transactional
  @Override
  public void attachPerson(Long personId, Long catId) {
    Person person = personRepository.findById(personId)
        .orElseThrow(() -> new NullPointerException("Person not found with ID: " + personId));
    Cat cat = catRepository.findById(catId)
        .orElseThrow(() -> new NullPointerException("there is no cat with id " + catId));

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
        .orElseThrow(() -> new NullPointerException("Person not found with ID: " + personId));
    Cat cat = catRepository.findById(catId)
        .orElseThrow(() -> new NullPointerException("there is no cat with id " + catId));

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
        .orElseThrow(() -> new NullPointerException("there is no cat with id " + id));
    catRepository.delete(cat);
  }
}







