package ru.aleksandra0KR.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.dto.PersonDto;
import ru.aleksandra0KR.entity.Person;
import ru.aleksandra0KR.mapper.CatMapper;
import ru.aleksandra0KR.mapper.PersonMapper;
import ru.aleksandra0KR.repository.PersonRepository;


@Service
public class PersonServiceImplementation implements PersonService {

  @Autowired
  PersonRepository personRepository;

  @Override
  public PersonDto findPersonByID(long id) {
    Person person = personRepository.findById(id)
        .orElseThrow(() -> new NullPointerException("Person not found with ID: " + id));
    return PersonMapper.asDto(person);
  }

  @Override
  public PersonDto findPersonByName(String name) {
    var person = personRepository.findByName(name);
    if (person == null) {
      throw new NullPointerException("there is no person with name " + name);
    }
    return PersonMapper.asDto(person);
  }

  @Transactional
  @Override
  public List<CatDto> findAllCats(long id) {
    Person person = personRepository.findById(id)
        .orElseThrow(() -> new NullPointerException("Person not found with ID: " + id));

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
        .orElseThrow(() -> new NullPointerException("Person not found with ID: " + person.getId()));
    personFromRepo.setName(person.getName());
    personFromRepo.setBirthdate(person.getBirthDate());
    personRepository.save(personFromRepo);
  }

  @Transactional
  @Override
  public void deletePerson(Long id) {
    Person personFromRepo = personRepository.findById(id)
        .orElseThrow(() -> new NullPointerException("Person not found with ID: " + id));
    personRepository.delete(personFromRepo);
  }
}
