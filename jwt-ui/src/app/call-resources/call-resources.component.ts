import { Component, OnInit } from '@angular/core';
import { AppService } from '../services/service';

@Component({
  selector: 'app-call-resources',
  templateUrl: './call-resources.component.html',
  styleUrls: ['./call-resources.component.css']
})
export class CallResourcesComponent implements OnInit {

  callResourceTextMessage: string = "";

  constructor(private appService: AppService) {
    this.getStudent();
  }

  ngOnInit() {
  }

  getStudent = function () {
    this.appService
      .postServiceCall(this.userDetails, "http://localhost:7787/", "student/all", true)
      .subscribe(data => {
        this.callResourceTextMessage = JSON.stringify(data);
        console.log(data);
        this.getCallResourceTextMessage();
      }, error => {
        this.callResourceTextMessage = "Token got Invalid";
        this.getCallResourceTextMessage();
      });
  }

  getCallResourceTextMessage = function():string {
    console.log(this.callResourceTextMessage);
    return this.callResourceTextMessage;
  }

}
