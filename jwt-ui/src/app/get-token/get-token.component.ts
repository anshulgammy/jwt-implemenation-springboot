import { Component, OnInit } from '@angular/core';
import { AppService } from '../services/service';

@Component({
  selector: 'app-get-token',
  templateUrl: './get-token.component.html',
  styleUrls: ['./get-token.component.css']
})
export class GetTokenComponent implements OnInit {

  constructor(private appService: AppService) { }

  ngOnInit() {
  }

  getTokenMessageForUI = function (): string {
    var message: string = "Click on 'Get Token' to fetch fresh token from Authentication Server";
    if (null != this.appService.getJwtTokenFromCookie() && "" != this.appService.getJwtTokenFromCookie()) {
      message = "Fetched Token now present in the cookie is: " + this.appService.getJwtTokenFromCookie();
    }
    return message;
  }

}
