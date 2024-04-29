package ru.aleksandra0KR.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aleksandra0KR.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

  Owner findByName(String name);
}
