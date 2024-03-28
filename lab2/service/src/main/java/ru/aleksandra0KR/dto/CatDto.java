package ru.aleksandra0KR.dto;

import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CatDto {
  private long id;
  private String name;
  private LocalDate birthDate;
  private String breed;
  private String color;
  private PersonDto catOwnerDto;
}
