import { Injectable } from '@angular/core';
import {Http, RequestOptions} from "@angular/http";
import {CurrentUserService} from "../shared/current-user.service";

@Injectable()
export class SummaryService {

    constructor(private http: Http, private currentUserService : CurrentUserService) { }

    getOnlineUsers() : Promise {
        let options = new RequestOptions({ withCredentials: this.currentUserService.isUserLoggedIn() });
        const url = 'http://localhost:8080/api/users/online';
        return this.http.get(url, options).toPromise();
    }

    getVacantGames() : void {

    }
}