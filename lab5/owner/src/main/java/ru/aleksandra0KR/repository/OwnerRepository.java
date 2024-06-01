package ru.aleksandra0KR.repository;

import ru.aleksandra0KR.dao.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

  Owner findByName(String name);
}
