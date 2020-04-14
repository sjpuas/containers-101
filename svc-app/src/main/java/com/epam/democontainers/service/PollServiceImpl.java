package com.epam.democontainers.service;

import com.epam.democontainers.domain.PollDto;
import com.epam.democontainers.domain.exception.PollNotFound;
import com.epam.democontainers.entity.PollEntity;
import com.epam.democontainers.entity.PollEntity.ChoiceNestedObject;
import com.epam.democontainers.repository.PollRepository;
import com.epam.democontainers.utils.ConvertUtils;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PollServiceImpl implements PollService {

  private ConvertUtils convertUtils;
  private PollRepository pollRepository;

  @Override
  public PollDto getPoll(String pollId) {
    Optional<PollEntity> pollEntityOptional = pollRepository.findById(pollId);
    return pollEntityOptional
        .map(convertUtils::convertToPoll)
        .orElseThrow(PollNotFound::new);
  }

  @Override
  public PollDto chooseChoice(String pollId, Long choiceOption) {
    Optional<PollEntity> pollEntityOptional = pollRepository.findById(pollId);
    pollEntityOptional.ifPresent(pollEntity -> vote(choiceOption, pollEntity));
    return pollEntityOptional
        .map(convertUtils::convertToPoll)
        .orElseThrow(PollNotFound::new);
  }

  @Override
  public List<PollDto> getPolls() {
    return convertUtils.convertToPolls(pollRepository.findAll());
  }

  @Override
  public PollDto savePoll(PollDto pollDto) {
    PollEntity pollEntity = PollEntity.builder()
        .name(pollDto.getName())
        .choices(convertUtils.convertToChoicesEntities(pollDto.getChoices()))
        .build();

    PollEntity pollEntitySaved = pollRepository.save(pollEntity);

    return convertUtils.convertToPoll(pollEntitySaved);
  }

  private void vote(Long choiceOption, PollEntity pollEntity) {
    List<ChoiceNestedObject> choiceEntities = Optional.ofNullable(pollEntity.getChoices())
        .orElse(Collections.emptyList());

    choiceEntities.stream()
        .filter(choiceNestedObject -> choiceNestedObject.getId().equals(choiceOption))
        .findFirst()
        .map(choiceNestedObject -> {
          Long votes = choiceNestedObject.getVotes();
          choiceNestedObject.setVotes(votes + 1);
          return choiceNestedObject;
        }).ifPresent(choiceNestedObject -> pollRepository.save(pollEntity));
  }

}
