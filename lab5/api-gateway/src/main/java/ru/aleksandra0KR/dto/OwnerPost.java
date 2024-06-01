package ru.aleksandra0KR.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Jacksonized
public class OwnerPost {

  @JsonProperty("name")
  private String name;

  @JsonProperty("birthDay")
  private LocalDate birthDay;

  @JsonProperty("id")
  private Long id;


}
