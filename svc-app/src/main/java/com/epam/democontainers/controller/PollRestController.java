package com.epam.democontainers.controller;

import com.epam.democontainers.domain.PollDto;
import com.epam.democontainers.service.PollService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/polls")
@AllArgsConstructor
public class PollRestController {

  private PollService pollService;

  @GetMapping
  public List<PollDto> getPolls() {
    return pollService.getPolls();
  }

  @PostMapping
  public PollDto savePoll(@RequestBody PollDto pollDto) {
    return pollService.savePoll(pollDto);
  }

  @GetMapping("/{pollId}")
  public PollDto getPoll(@PathVariable("pollId") String pollId) {
    return pollService.getPoll(pollId);
  }

  @PutMapping("/{pollId}/choices/{choiceOption}/vote")
  public PollDto chooseChoice(
      @PathVariable("pollId") String pollId,
      @PathVariable("choiceOption") Long choiceOption) {
    return pollService.chooseChoice(pollId, choiceOption);
  }


}
