import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { FacebookUserModel } from "./facebookuser";


@Injectable()
export class FacebookDataService{
    constructor(private http: HttpClient){}

    getAllUserList():  Promise<FacebookUserModel[]> {
        return this.http.get('http://localhost:8080/facebookweb/FindAllUser')
          .toPromise()
          .then(response => response as FacebookUserModel[]);
         
      }
}