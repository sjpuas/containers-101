package com.epam.democontainers.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PollDto {

  String id;
  String name;
  List<ChoiceDto> choices;

}
