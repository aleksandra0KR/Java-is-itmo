package ru.aleksandra0KR.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.dto.PersonDto;
import ru.aleksandra0KR.entity.Person;
import ru.aleksandra0KR.exceptions.PersonDoesntExistException;
import ru.aleksandra0KR.mapper.CatMapper;
import ru.aleksandra0KR.mapper.PersonMapper;
import ru.aleksandra0KR.repository.PersonRepository;


@Service
public class PersonServiceImplementation implements PersonService {

  final
  PersonRepository personRepository;

  public PersonServiceImplementation(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Override
  public PersonDto findPersonByID(long id) {
    Person person = personRepository.findById(id)
        .orElseThrow(() -> new PersonDoesntExistException(id));
    return PersonMapper.asDto(person);
  }

  @Override
  public PersonDto findPersonByName(String name) {
    var person = personRepository.findByName(name);
    if (person == null) {
      throw new PersonDoesntExistException(name);
    }
    return PersonMapper.asDto(person);
  }

  @Transactional
  @Override
  public List<CatDto> findAllCats(long id) {
    Person person = personRepository.findById(id)
        .orElseThrow(() -> new PersonDoesntExistException(id));

    return person.getCats()
        .stream()
        .map(CatMapper::asDto)
        .collect(Collectors.toList());
  }

  @Override
  public PersonDto addPerson(PersonDto person) {
    Person dao = personRepository.save(PersonMapper.asDao(person));
    Long generatedId = dao.getPerson_id();
    person.setId(generatedId);
    return person;
  }

  @Transactional
  @Override
  public void updatePerson(PersonDto person) {
    Person personFromRepo = personRepository.findById(person.getId())
        .orElseThrow(() -> new PersonDoesntExistException(person.getId()));
    personFromRepo.setName(person.getName());
    personFromRepo.setBirthdate(person.getBirthDate());
    personRepository.save(personFromRepo);
  }

  @Transactional
  @Override
  public void deletePerson(Long id) {
    Person personFromRepo = personRepository.findById(id)
        .orElseThrow(() -> new PersonDoesntExistException(id));
    personRepository.delete(personFromRepo);
  }
}