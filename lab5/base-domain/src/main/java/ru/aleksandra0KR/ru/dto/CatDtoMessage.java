package ru.aleksandra0KR.ru.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@Data
@Builder
@Jacksonized
public class CatDtoMessage {
  private Long id;
  private String name;
  private Long ownerID;
  private LocalDate birthDay;
  private String breed;
  private String catColor;

}