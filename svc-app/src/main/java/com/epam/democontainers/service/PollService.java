package com.epam.democontainers.service;

import com.epam.democontainers.domain.PollDto;
import java.util.List;

public interface PollService {

  PollDto getPoll(String pollId);

  PollDto chooseChoice(String pollId, Long choiceOption);

  List<PollDto> getPolls();

  PollDto savePoll(PollDto pollDto);
}
