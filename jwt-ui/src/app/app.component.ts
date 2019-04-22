import { Component } from "@angular/core";
import { AppService } from "./services/service";
import { UserDetails } from "./beans/userdetails";
import { CallResourcesComponent } from './call-resources/call-resources.component';

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"]
})
export class AppComponent {
  title = "jwt-ui";
  userDetails: UserDetails;

  constructor(private appService: AppService) {}

  getToken = function() {
    // call authorization server to get JWT Token.
    this.appService.removeJwtTokenCookie();
    this.appService
      .postServiceCall(this.userDetails, "http://localhost:8585/", "token/get", false)
      .subscribe(data => {
        const userDetails: UserDetails = data;
        this.appService.putJwtTokenInCookie(userDetails.jwtToken);
      });
  };

  getStudent = function() {
    const callResourceObj = new CallResourcesComponent(this.appService);
    callResourceObj.getStudent();
  };

  ngOnInit() {
    this.appService.removeJwtTokenCookie();
    this.userDetails = new UserDetails();
  }
}
