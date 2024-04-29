package ru.aleksandra0KR.mapper;

import java.util.ArrayList;
import java.util.Objects;
import lombok.experimental.UtilityClass;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.model.Cat;

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
