package com.epam.democontainers.utils;

import com.epam.democontainers.domain.ChoiceDto;
import com.epam.democontainers.domain.PollDto;
import com.epam.democontainers.entity.PollEntity;
import com.epam.democontainers.entity.PollEntity.ChoiceNestedObject;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ConvertUtils {

  public List<PollDto> convertToPolls(List<PollEntity> polls) {
    List<PollEntity> choiceEntities = Optional.ofNullable(polls)
        .orElse(Collections.emptyList());

    return choiceEntities.stream()
        .map(this::convertToPoll)
        .collect(Collectors.toList());
  }


  public PollDto convertToPoll(PollEntity pollEntity) {
    return PollDto.builder()
        .id(pollEntity.getId())
        .name(pollEntity.getName())
        .choices(convertToChoices(pollEntity.getChoices()))
        .build();
  }

  private List<ChoiceDto> convertToChoices(List<ChoiceNestedObject> choices) {
    List<ChoiceNestedObject> choiceEntities = Optional.ofNullable(choices)
        .orElse(Collections.emptyList());

    return choiceEntities.stream()
        .map(this::convertToChoice)
        .collect(Collectors.toList());
  }

  private ChoiceDto convertToChoice(ChoiceNestedObject choiceNestedObject) {
    return ChoiceDto.builder()
        .choiceId(choiceNestedObject.getId())
        .name(choiceNestedObject.getName())
        .votes(choiceNestedObject.getVotes())
        .build();
  }

  public List<ChoiceNestedObject> convertToChoicesEntities(List<ChoiceDto> choices) {
    List<ChoiceDto> choiceDtos = Optional.ofNullable(choices)
        .orElse(Collections.emptyList());

    AtomicLong counter = new AtomicLong(1);
    return choiceDtos.stream()
        .map(choice -> convertToChoiceEntity(counter, choice))
        .collect(Collectors.toList());
  }

  private ChoiceNestedObject convertToChoiceEntity(AtomicLong counter, ChoiceDto choiceDto) {
    return ChoiceNestedObject.builder()
        .id(counter.getAndIncrement())
        .name(choiceDto.getName())
        .votes(0L)
        .build();
  }
}
