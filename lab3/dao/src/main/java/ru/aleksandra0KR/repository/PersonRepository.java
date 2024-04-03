package ru.aleksandra0KR.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aleksandra0KR.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

  public Person findByName(String name);
}
