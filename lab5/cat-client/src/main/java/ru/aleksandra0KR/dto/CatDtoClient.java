package ru.aleksandra0KR.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@ToString
@Jacksonized
@NoArgsConstructor
@Builder
public class CatDtoClient {

  @JsonProperty("id")
  private Long id;
  @NotNull
  @NotEmpty
  @JsonProperty("name")
  private String name;

  @Past
  @DateTimeFormat(pattern = "yyyy-mm-dd")
  @JsonProperty("birthDate")
  private LocalDate birthDate;

  @JsonProperty("breed")
  private String breed;
  @JsonProperty("color")
  private String color;
  @JsonProperty("owner_id")
  private Long owner;




}
