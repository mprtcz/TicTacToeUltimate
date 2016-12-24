import { Injectable } from '@angular/core';
import {Http, RequestOptions} from "@angular/http";
import {CurrentUserService} from "../shared/current-user.service";
import {ServerAddressService} from "../shared/server-address.service";

@Injectable()
export class SummaryService {

    constructor(private http: Http, private currentUserService : CurrentUserService, private serverAddressService : ServerAddressService) { }

    getOnlineUsers() : Promise {
        let options = new RequestOptions({ withCredentials: this.currentUserService.isUserLoggedIn() });
        const url = this.serverAddressService.serverAddress + '/api/users/online';
        return this.http.get(url, options).toPromise();
    }

    getVacantGames() : void {

    }
}