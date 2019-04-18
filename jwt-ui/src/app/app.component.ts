import { Component } from "@angular/core";
import { AppService } from "./services/service";
import { UserDetails } from "./beans/userdetails";

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
      .postServiceCall(this.userDetails, "http://localhost:8585/", "token/get")
      .subscribe(data => {
        const userDetails: UserDetails = data;
        this.appService.putJwtTokenInCookie(userDetails.jwtToken);
      });
  };

  ngOnInit() {
    this.appService.removeJwtTokenCookie();
    this.userDetails = new UserDetails();
  }
}
