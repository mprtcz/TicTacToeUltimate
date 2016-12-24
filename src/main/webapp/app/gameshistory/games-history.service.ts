import { Injectable } from '@angular/core';
import {Http, RequestOptions} from "@angular/http";
import {CurrentUserService} from "../shared/current-user.service";
import {ServerAddressService} from "../shared/server-address.service";

@Injectable()
export class GamesHistoryService {

    constructor(private http: Http, private currentUserService : CurrentUserService, private serverAddressService : ServerAddressService) { }

    getDataFromServer(username: String) : Promise {
        let options = new RequestOptions({ withCredentials: this.currentUserService.isUserLoggedIn() });
        const url = this.serverAddressService.serverAddress + '/games/' + username;
        return this.http.get(url, options).toPromise();
    }

}