package ru.aleksandra0KR.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class PersonDto {

  @JsonIgnore
  private long id;
  @NotNull
  @NotEmpty
  private String name;

  @Past
  @DateTimeFormat(pattern = "yyyy-mm-dd")
  private LocalDate birthDate;
}
