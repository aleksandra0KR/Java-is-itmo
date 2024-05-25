package ru.aleksandra0KR.ru.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class PersonDtoMessage {

  Long Id;

  @NotNull
  @NotEmpty
  String username;

  Long ownerID;

  @NotNull
  @NotEmpty
  String roles = "ROLE_USER";



}
