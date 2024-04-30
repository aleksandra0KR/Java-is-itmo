package ru.aleksandra0KR.service;

import java.security.Principal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aleksandra0KR.dto.OwnerDto;
import ru.aleksandra0KR.dto.PersonDto;
import ru.aleksandra0KR.exceptions.OwnerPersonConnectionException;
import ru.aleksandra0KR.mapper.PersonMapper;
import ru.aleksandra0KR.model.Owner;
import ru.aleksandra0KR.model.Person;
import ru.aleksandra0KR.repository.OwnerRepository;
import ru.aleksandra0KR.repository.PersonRepository;

@Service
public class PersonService {

  private final PersonRepository personRepository;
  private final OwnerRepository ownerRepository;
  private final BCryptPasswordEncoder passwordEncoder;
  private final OwnerServiceImplementation ownerServiceImplementation;

  public PersonService(BCryptPasswordEncoder passwordEncoder, PersonRepository personRepository,
      OwnerServiceImplementation ownerServiceImplementation,
      OwnerRepository ownerRepository) {
    this.passwordEncoder = passwordEncoder;
    this.personRepository = personRepository;
    this.ownerServiceImplementation = ownerServiceImplementation;
    this.ownerRepository = ownerRepository;
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

  @Transactional
  public void connectOwner(Long id, Principal principal) {
    Person person = getPersonByName(principal.getName());
    Owner owner = ownerRepository.getById(id);
    if (person.getOwner() != null) {
      throw new OwnerPersonConnectionException(person.getOwner().getName());
    }

    person.setOwner(owner);
    owner.setPerson(person);
    ownerRepository.save(owner);
    personRepository.save(person);
  }

  @Transactional
  public void addOwner(OwnerDto ownerDto, Principal principal) {
    ownerServiceImplementation.addPerson(ownerDto);
    Owner owner = ownerRepository.getById(ownerDto.getId());
    Person person = getPersonByName(principal.getName());
    person.setOwner(owner);
    owner.setPerson(person);
    ownerRepository.save(owner);
    personRepository.save(person);
  }

}
