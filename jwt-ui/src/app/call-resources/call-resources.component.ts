import { Component, OnInit } from '@angular/core';
import { AppService } from '../services/service';

@Component({
  selector: 'app-call-resources',
  templateUrl: './call-resources.component.html',
  styleUrls: ['./call-resources.component.css']
})
export class CallResourcesComponent implements OnInit {

  callResourceTextMessage: string = "Click on Call Student Web Service to make call to Student Web Service.";

  constructor(private appService: AppService) {
  }

  ngOnInit() {
  }

  getStudent = function () {
    this.appService
      .postServiceCall(this.userDetails, "http://localhost:7787/", "student/all", true)
      .subscribe(data => {
        alert(JSON.stringify(data));
      }, error => {
        alert("Token got Invalid");
      });
  }

}
