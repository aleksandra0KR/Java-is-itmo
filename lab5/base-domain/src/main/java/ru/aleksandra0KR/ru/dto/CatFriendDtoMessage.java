package ru.aleksandra0KR.ru.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@Data
@Builder
@Jacksonized
public class CatFriendDtoMessage {
  private Long catId;
  private Long friendId;
}
