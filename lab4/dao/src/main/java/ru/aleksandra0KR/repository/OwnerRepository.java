package ru.aleksandra0KR.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aleksandra0KR.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
