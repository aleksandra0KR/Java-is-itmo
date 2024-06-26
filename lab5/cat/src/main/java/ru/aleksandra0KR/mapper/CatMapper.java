package ru.aleksandra0KR.mapper;

import ru.aleksandra0KR.dao.Cat;
import ru.aleksandra0KR.dto.CatDto;
import java.util.ArrayList;
import java.util.Objects;
import lombok.experimental.UtilityClass;
import ru.aleksandra0KR.dto.CatDtoClient;

@UtilityClass
public class CatMapper {

  public CatDtoClient asDto(Cat cat) {
    Objects.requireNonNull(cat);
    return new CatDtoClient(cat.getId(), cat.getName(), cat.getBirthday(), cat.getBreed(),
        cat.getColor(), cat.getOwnerId());
  }

  public Cat asDao(CatDto cat) {
    Objects.requireNonNull(cat);
    return new Cat(cat.getId(), cat.getName(), cat.getColor(), cat.getBreed(), cat.getBirthDate(),
        cat.getOwnerID(), new ArrayList<>());
  }
}
