package ru.aleksandra0KR;

import java.util.List;
import ru.aleksandra0KR.dto.OwnerDto;

public interface OwnerClient {

  OwnerDto getSelf();
  OwnerDto adminGetById(long uuid);

}