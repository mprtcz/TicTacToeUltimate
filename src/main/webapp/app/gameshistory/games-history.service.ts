import { Injectable } from '@angular/core';
import {Http, RequestOptions} from "@angular/http";
import {CurrentUserService} from "../shared/current-user.service";

@Injectable()
export class GamesHistoryService {

    constructor(private http: Http, private currentUserService : CurrentUserService) { }

    getDataFromServer(username: String) : Promise {
        let options = new RequestOptions({ withCredentials: this.currentUserService.isUserLoggedIn() });
        const url = 'http://localhost:8080/games/' + username;
        return this.http.get(url, options).toPromise();
    }

}