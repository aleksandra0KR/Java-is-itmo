package ru.aleksandra0KR.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aleksandra0KR.model.Person;
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
  public void addUser(Person user) {
    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    personRepository.save(user);
  }

  public Person getPersonByName(String username) {
    return personRepository.findByUsername(username);
  }
}
