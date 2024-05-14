package aleksandra0KR.mapper;

import aleksandra0KR.dao.Cat;
import aleksandra0KR.dto.CatDto;
import java.util.ArrayList;
import java.util.Objects;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CatMapper {

  public CatDto asDto(Cat cat) {
    Objects.requireNonNull(cat);
    return new CatDto(cat.getId(), cat.getName(), cat.getBirthday(), cat.getBreed(),
        cat.getColor());
  }

  public Cat asDao(CatDto cat) {
    Objects.requireNonNull(cat);
    return new Cat(cat.getId(), cat.getName(), cat.getColor(), cat.getBreed(), cat.getBirthDate(),
        null, new ArrayList<>());
  }
}
