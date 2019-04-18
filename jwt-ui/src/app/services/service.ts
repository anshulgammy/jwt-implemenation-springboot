import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { Injectable } from '@angular/core';
const getTokenOption = {
  headers: new HttpHeaders({
    "Content-Type": "application/json"
  })
};
import { CookieService } from 'ngx-cookie-service';
@Injectable()
export class AppService {
    
  constructor(private http: HttpClient, private cookieService: CookieService) {}

  jwtToken: string = null;
  callResourcesOption = {
    headers: new HttpHeaders({
      Authorization: "Bearer " + this.jwtToken
    })
  };

  putJwtTokenInCookie = function(jwtToken) {
    this.jwtToken = jwtToken;
    this.cookieService.set( 'jwtToken', jwtToken );
  };

  removeJwtTokenCookie = function() {
      this.cookieService.delete('jwtToken');
  }

  getJwtTokenFromCookie = function() {
    return this.cookieService.get('jwtToken');
  };

  /* Function to call the REST API for POST type */
  postServiceCall(
    requestBody: Object,
    baseUrl: string,
    resourceUrl: string
  ): Observable<Object> {
    var completeEndpointUrl: string = baseUrl + resourceUrl;
    return this.http.post(completeEndpointUrl, requestBody, getTokenOption);
  }
}
