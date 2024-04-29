package ru.aleksandra0KR.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aleksandra0KR.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

  Person findByUsername(String username);
}
