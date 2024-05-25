package ru.aleksandra0KR.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
