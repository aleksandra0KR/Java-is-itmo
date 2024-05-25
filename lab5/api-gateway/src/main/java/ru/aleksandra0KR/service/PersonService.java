package ru.aleksandra0KR.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aleksandra0KR.dao.Person;
import ru.aleksandra0KR.dto.PersonDto;
import ru.aleksandra0KR.mapper.PersonMapper;
import ru.aleksandra0KR.repository.PersonRepository;


@Service
public class PersonService {

  private final PersonRepository personRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public PersonService(BCryptPasswordEncoder passwordEncoder, PersonRepository personRepository) {
    this.passwordEncoder = passwordEncoder;
    this.personRepository = personRepository;
  }

  @Transactional
  public void addUser(PersonDto personDto) {
    Person person = PersonMapper.asDao(personDto);
    person.setPassword(new BCryptPasswordEncoder().encode(person.getPassword()));
    personRepository.save(person);
  }

  public Person getPersonByName(String username) {
    return personRepository.findByUsername(username);
  }

  /*
  @Transactional
  public void addOwner(Long ownerId, Principal principal) {
    Person person = getPersonByName(principal.getName());
    person.setOwnerId(ownerId);
    personRepository.save(person);
  }*/

}
