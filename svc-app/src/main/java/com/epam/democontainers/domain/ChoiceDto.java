package com.epam.democontainers.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ChoiceDto {

  Long choiceId;
  String name;
  Long votes;

}
