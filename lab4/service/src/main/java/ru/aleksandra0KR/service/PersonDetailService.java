package ru.aleksandra0KR.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.aleksandra0KR.model.Person;
import ru.aleksandra0KR.repository.PersonRepository;

@Service
public class PersonDetailService implements UserDetailsService {
  @Autowired
  PersonRepository personRepository;

 // @Autowired
  //PasswordEncoder passwordEncoder;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Person person = personRepository.findByUsername(username);
    return new PersonDetailsCats(person);
  }

  /*@Transactional
  public void addUser(Person user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    personRepository.save(user);
  }

 /*final
  PersonRepository personRepository;

  final
  OwnerRepository ownerRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  final OwnerService ownerService;

  public PersonService(OwnerService ownerService, PersonRepository personRepository,
      OwnerRepository ownerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.ownerService = ownerService;
    this.personRepository = personRepository;
    this.ownerRepository = ownerRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }


  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Person user = personRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("Could not find user");
    }
    return new UserDetailsCats(user);
  }

  public Person findByUsername(String username) {
    return personRepository.findByUsername(username);
  }


  @Transactional
  public void addUser(Person user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.setRoles(new HashSet());
   // Role role = Role.ROLE_USER;
  //  user.setRoles(new HashSet<>(Collections.singletonList(role)));
    personRepository.save(user);
  }

 /* public Person getPersonByUsername(String username) {
    Owner catOwner = ownerRepository.findByName(username);
    user.setCatOwner(catOwner);
    catOwner.setUser(user);
    catOwnerRepository.save(catOwner);
    userRepository.save(user);
    return personRepository.findByUsername(username);
  }*/
}

