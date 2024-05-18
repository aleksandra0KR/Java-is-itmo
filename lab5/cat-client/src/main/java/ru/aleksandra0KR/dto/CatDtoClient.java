package ru.aleksandra0KR.dto;

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

  private long id;
  @NotNull
  @NotEmpty
  private String name;

  @Past
  @DateTimeFormat(pattern = "yyyy-mm-dd")
  private LocalDate birthDate;

  private String breed;
  private String color;
  private Long owner;
  private List<Long> friends;




}
