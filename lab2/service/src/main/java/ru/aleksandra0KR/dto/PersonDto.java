package ru.aleksandra0KR.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class PersonDto {

  private long id;
  private String name;
  private LocalDate birthDate;
}
