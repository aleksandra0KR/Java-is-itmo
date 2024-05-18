package ru.aleksandra0KR.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Builder
public class OwnerDtoGateway {
  private long id;
  @NotNull
  @NotEmpty
  private String name;

  @Past
  @DateTimeFormat(pattern = "yyyy-mm-dd")
  private LocalDate birthDate;

  private List<Long> cats;
}
