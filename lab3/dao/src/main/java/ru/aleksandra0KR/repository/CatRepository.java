package ru.aleksandra0KR.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aleksandra0KR.entity.Cat;


@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {

  List<Cat> findCatByColor(String color);

  List<Cat> findCatByBreed(String color);

  List<Cat> findCatByName(String name);

  List<Cat> getFriendsById(Long id);

}
