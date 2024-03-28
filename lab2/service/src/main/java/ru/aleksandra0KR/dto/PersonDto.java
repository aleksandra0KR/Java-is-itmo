package ru.aleksandra0KR.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonDto {
  private long id;
  private String name;
  private LocalDate birthDate;
}
