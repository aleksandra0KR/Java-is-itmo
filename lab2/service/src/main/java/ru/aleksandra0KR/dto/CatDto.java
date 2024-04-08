package ru.aleksandra0KR.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class CatDto {

  private long id;
  private String name;
  private LocalDate birthDate;
  private String breed;
  private String color;
  private PersonDto catOwnerDto;

  public CatDto(String name, String breed, String color, LocalDate birthDate, long id) {
    this.id = id;
    this.name = name;
    this.breed = breed;
    this.color = color;
    this.birthDate = birthDate;
    catOwnerDto = null;
  }

  public CatDto(String name, String breed, String color, LocalDate birthDate) {
    this.name = name;
    this.breed = breed;
    this.color = color;
    this.birthDate = birthDate;
    catOwnerDto = null;
  }

}
