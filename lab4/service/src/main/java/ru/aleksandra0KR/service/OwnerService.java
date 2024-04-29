package ru.aleksandra0KR.service;

import java.util.List;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.dto.OwnerDto;

public interface OwnerService {

  OwnerDto findPersonByID(long id);

  OwnerDto findPersonByName(String name);

  List<CatDto> findAllCats(long id);

  OwnerDto addPerson(OwnerDto ownerDto);

  void updatePerson(OwnerDto ownerDto);

  void deletePerson(Long id);
}
