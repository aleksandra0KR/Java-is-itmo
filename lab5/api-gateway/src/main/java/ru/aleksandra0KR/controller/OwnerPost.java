package ru.aleksandra0KR.controller;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class OwnerPost {

  @JsonProperty("name")
  private String name;

  @JsonProperty("birthDay")
  private LocalDate birthDay;

  @JsonProperty("id")
  private Long id;

}
