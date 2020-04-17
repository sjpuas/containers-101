import { Component, OnInit } from '@angular/core';
import { PollApiService } from './api-client/poll-api';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'poll-app';
  type = 'PieChart';
  width = 300;
  height = 200;

  polls: any = [];

  constructor(
    public restApi: PollApiService
  ) { }

  ngOnInit() {
    this.restApi.getPolls().subscribe((data: {}) => {
      this.polls = data;
      this.polls.forEach(poll => {
        poll.data = [];
        poll.choices.forEach(choice => {
          poll.data.push([choice.name, choice.votes]);
        });
      });
    });
  }

  vote(pollId, choiceId) {
    console.info(pollId, choiceId);
    this.restApi.vote(pollId, choiceId).subscribe((data: {}) => {
      this.ngOnInit();
    });
  }

}
