package ru.aleksandra0KR;

import ru.aleksandra0KR.dto.OwnerDtoClient;

public interface OwnerClient {

  OwnerDtoClient GetOwnerById(long id);
  OwnerDtoClient GetOwnerByName(String name);

}