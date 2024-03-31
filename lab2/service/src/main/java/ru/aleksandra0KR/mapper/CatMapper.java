package ru.aleksandra0KR.mapper;

import java.util.ArrayList;
import java.util.Objects;
import lombok.experimental.UtilityClass;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.dto.PersonDto;
import ru.aleksandra0KR.entity.Cat;
import ru.aleksandra0KR.entity.Person;

@UtilityClass
public class CatMapper {

  public CatDto asDto(Cat cat) {
    Objects.requireNonNull(cat);
    PersonDto owner = PersonMapper.asDto(cat.getPerson());
    return new CatDto(cat.getId(), cat.getName(), cat.getBirthday(), cat.getBreed(),
        cat.getColor(), owner);
  }

  public Cat asDao(CatDto cat) {
    Objects.requireNonNull(cat);
    Person owner = PersonMapper.asDao(cat.getCatOwnerDto());
    return new Cat(cat.getId(), cat.getName(), cat.getBreed(), cat.getColor(), cat.getBirthDate(),
        owner, new ArrayList<>());
  }
}
