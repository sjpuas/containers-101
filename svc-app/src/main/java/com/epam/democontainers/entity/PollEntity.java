package com.epam.democontainers.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "polls")
public class PollEntity {

  @Id
  private String id;
  private String name;
  private List<ChoiceNestedObject> choices;

  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  public static class ChoiceNestedObject {

    private Long id;
    String name;
    Long votes;


  }

}
