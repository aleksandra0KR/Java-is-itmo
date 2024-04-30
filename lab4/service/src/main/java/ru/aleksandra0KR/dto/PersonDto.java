package ru.aleksandra0KR.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class PersonDto {

  @NotNull
  @NotEmpty
  String username;

  @NotNull
  @NotEmpty
  String password;

  @NotNull
  @NotEmpty
  String roles;

  public PersonDto(String username, String password) {
    this.roles = "ROLE_USER";
    this.username = username;
    this.password = password;
  }
}
