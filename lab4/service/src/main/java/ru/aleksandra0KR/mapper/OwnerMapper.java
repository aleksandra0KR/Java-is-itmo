package ru.aleksandra0KR.mapper;

import java.util.ArrayList;
import java.util.Objects;
import lombok.experimental.UtilityClass;
import ru.aleksandra0KR.dto.OwnerDto;
import ru.aleksandra0KR.model.Owner;

@UtilityClass

public class OwnerMapper {

  public OwnerDto asDto(Owner person) {
    Objects.requireNonNull(person);
    return new OwnerDto(person.getOwner_id(), person.getName(), person.getBirthday(),
        person.getPerson()
            .getRoles());
  }

  public Owner asDao(OwnerDto person) {
    Objects.requireNonNull(person);
    return new Owner(person.getId(), person.getName(), person.getBirthDate(), new ArrayList<>(),
        null);
  }
}
