package ru.aleksandra0KR.dto;

import java.time.LocalDate;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@AllArgsConstructor
@Builder
@Jacksonized
public class OwnerDtoClient {

  private Long owner_id;

  private String name;

  private LocalDate birthday;

  private Set<Long> cats;

  private Long person_id;

}