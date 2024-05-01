package ru.aleksandra0KR.service;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.exceptions.CatsPrivateInformationException;
import ru.aleksandra0KR.model.Cat;
import ru.aleksandra0KR.model.Owner;
import ru.aleksandra0KR.exceptions.CatDoesntExistsException;
import ru.aleksandra0KR.exceptions.PersonDoesntExistException;
import ru.aleksandra0KR.mapper.CatMapper;
import ru.aleksandra0KR.model.Person;
import ru.aleksandra0KR.repository.CatRepository;
import ru.aleksandra0KR.repository.OwnerRepository;

@Service
public class CatServiceImplementation implements CatService {

  final
  CatRepository catRepository;

  final
  OwnerRepository ownerRepository;

  final
  PersonService personService;

  public CatServiceImplementation(CatRepository catRepository, OwnerRepository ownerRepository,
      PersonService personService) {
    this.catRepository = catRepository;
    this.ownerRepository = ownerRepository;
    this.personService = personService;
  }


  public CatDto findCatByID(long id) {
    Cat cat = catRepository.findById(id)
        .orElseThrow(() -> new CatDoesntExistsException(id));
    return CatMapper.asDto(cat);
  }

  public Long OwnerId(Principal principal) {
    Person person = personService.getPersonByName(principal.getName());
    if (person != null && person.getOwner() != null) {
      return person.getOwner().getOwner_id();
    }
    throw new PersonDoesntExistException(principal.getName());
  }

  @Override
  public List<CatDto> findCatsByColorOrBreedOrName(Principal principal, String color, String breed,
      String name) {
    if (color == null && breed == null && name == null) {
      throw new CatDoesntExistsException("");
    }
    Long id = OwnerId(principal);
    if (breed != null) {
      return catRepository.findCatByBreed(breed).stream()
          .filter(cat -> cat.getOwner() != null && Objects.equals(cat.getOwner().getOwner_id(), id))
          .map(CatMapper::asDto)
          .collect(Collectors.toList());
    } else if (color != null) {
      return catRepository.findCatByColor(color).stream()
          .filter(cat -> cat.getOwner() != null && Objects.equals(cat.getOwner().getOwner_id(), id))
          .map(CatMapper::asDto)
          .collect(Collectors.toList());
    } else {
      return catRepository.findCatByName(name).stream()
          .filter(cat -> cat.getOwner() != null && Objects.equals(cat.getOwner().getOwner_id(), id))
          .map(CatMapper::asDto)
          .collect(Collectors.toList());
    }
  }

  public CatDto addCat(CatDto cat) {
    Cat dao = catRepository.save(CatMapper.asDao(cat));
    Long generatedId = dao.getId();
    cat.setId(generatedId);
    return cat;
  }

  @Transactional
  public void updateCat(Principal principal, CatDto cat) {
    Long id = OwnerId(principal);
    Cat catFromRepo = catRepository.findById(cat.getId())
        .orElseThrow(() -> new CatDoesntExistsException(cat.getId()));
    if (catFromRepo.getOwner() != null && !Objects.equals(catFromRepo.getOwner().getOwner_id(),
        id)) {
      throw new CatsPrivateInformationException();
    }
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
  public void attachPerson(Long catId, Long personId) {
    Owner person = ownerRepository.findById(personId)
        .orElseThrow(() -> new PersonDoesntExistException(personId));
    Cat cat = catRepository.findById(catId)
        .orElseThrow(() -> new CatDoesntExistsException(catId));

    cat.setOwner(person);
    var cats = person.getCats();
    cats.add(cat);
    person.setCats(cats);
    catRepository.save(cat);
    ownerRepository.save(person);
  }

  @Transactional
  @Override
  public void detachPerson(Long catId, Long personId) {
    Owner person = ownerRepository.findById(personId)
        .orElseThrow(() -> new PersonDoesntExistException(personId));
    Cat cat = catRepository.findById(catId)
        .orElseThrow(() -> new CatDoesntExistsException(catId));
    cat.setOwner(null);
    var cats = person.getCats();
    cats.remove(cat);
    person.setCats(cats);
    catRepository.save(cat);
    ownerRepository.save(person);
  }

  public List<CatDto> getAllFriends(long id) {
    return catRepository.getFriendsById(id)
        .stream()
        .map(CatMapper::asDto)
        .collect(Collectors.toList());
  }

  @Transactional
  public void deleteCat(Principal principal, long id) {

    Long personId = OwnerId(principal);

    Cat cat = catRepository.findById(id)
        .orElseThrow(() -> new CatDoesntExistsException(id));

    if (cat.getOwner() != null && !Objects.equals(personId, cat.getOwner().getOwner_id())) {
      throw new CatsPrivateInformationException();
    }

    catRepository.delete(cat);
  }
}









