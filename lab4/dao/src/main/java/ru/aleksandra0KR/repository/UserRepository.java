package ru.aleksandra0KR.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aleksandra0KR.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByName(String name);
}
