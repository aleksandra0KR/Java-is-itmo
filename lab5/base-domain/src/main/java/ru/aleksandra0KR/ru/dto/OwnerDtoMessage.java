package ru.aleksandra0KR.ru.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@Data
@Builder
@Jacksonized
public class OwnerDtoMessage {
  @JsonProperty("id")
  Long id;
  @JsonProperty("name")
  String name;
  @JsonProperty("birthDay")
  LocalDate birthDay;
}
