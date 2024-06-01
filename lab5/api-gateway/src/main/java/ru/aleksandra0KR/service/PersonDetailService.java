package ru.aleksandra0KR.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.aleksandra0KR.dao.Person;
import ru.aleksandra0KR.repository.PersonRepository;


@Service
public class PersonDetailService implements UserDetailsService {

  @Autowired
  PersonRepository personRepository;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Person person = personRepository.findByUsername(username);
    return new PersonDetailsCats(person);
  }

}

