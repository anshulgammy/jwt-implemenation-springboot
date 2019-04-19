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
      }, error => {
        this.callResourceTextMessage = "Token got Invalid";
      });
  }

}
