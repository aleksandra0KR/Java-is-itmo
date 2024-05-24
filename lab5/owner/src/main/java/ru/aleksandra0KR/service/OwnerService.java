package ru.aleksandra0KR.service;

import ru.aleksandra0KR.dto.OwnerDto;
import java.util.List;
import ru.aleksandra0KR.dto.OwnerDtoClient;
import ru.aleksandra0KR.ru.dto.OwnerDtoMessage;

public interface OwnerService {

  OwnerDtoClient findPersonByID(long id);

  OwnerDto findPersonByName(String name);

  OwnerDto addPerson(OwnerDto ownerDto);

  void updatePerson(OwnerDto ownerDto);

  void deletePerson(Long id);
  void addOwner(OwnerDtoMessage ownerDtoMessage);
}
