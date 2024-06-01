package ru.aleksandra0KR.mapper;

import java.util.HashSet;
import ru.aleksandra0KR.dao.Owner;
import ru.aleksandra0KR.dto.OwnerDto;
import java.util.Objects;
import lombok.experimental.UtilityClass;

@UtilityClass

public class OwnerMapper {

  public OwnerDto asDto(Owner person) {
    Objects.requireNonNull(person);
    return new OwnerDto(person.getOwner_id().longValue(), person.getName(), person.getBirthday(),
        new HashSet<>());
  }

  public Owner asDao(OwnerDto person) {
    Objects.requireNonNull(person);
    return new Owner(person.getId(), person.getName(), person.getBirthDate(),
        null);
  }
}
