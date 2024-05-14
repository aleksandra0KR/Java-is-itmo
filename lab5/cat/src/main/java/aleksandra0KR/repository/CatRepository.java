package aleksandra0KR.repository;

import aleksandra0KR.dao.Cat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {

  List<Cat> findCatByColor(String color);

  List<Cat> findCatByBreed(String color);

  List<Cat> findCatByName(String name);

  @Query(value = "SELECT cat.* FROM cats cat WHERE cat.id IN ( SELECT friend_id FROM friends WHERE cat_id = :id)", nativeQuery = true)
  List<Cat> getFriendsById(@Param("id") Long id);

}
