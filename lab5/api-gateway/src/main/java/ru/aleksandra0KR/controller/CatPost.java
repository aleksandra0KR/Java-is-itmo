package ru.aleksandra0KR.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.function.LongPredicate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CatPost {

  @JsonProperty("name")
  private String name;

  @JsonProperty("birthDay")
  private LocalDate birthDay;

  @JsonProperty("breed")

  private String breed;

  @JsonProperty("color")
  private String color;

  @JsonProperty("id")
  private Long id;

  @JsonProperty("ownerID")
  private Long ownerID;

}
