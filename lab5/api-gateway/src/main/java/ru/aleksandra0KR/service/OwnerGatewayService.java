package ru.aleksandra0KR.service;

import java.security.Principal;
import ru.aleksandra0KR.controller.OwnerPost;
import ru.aleksandra0KR.dto.OwnerDtoClient;

public interface OwnerGatewayService {
  OwnerDtoClient findPersonById( Long id, Principal principal);
  OwnerDtoClient findPersonByName(String name, Principal principal);
  void update(OwnerPost ownerPost, Principal principal);
  void deleteOwner(Principal principal, long id);
  void addOwner(OwnerPost ownerPost);
}
