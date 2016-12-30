import {Injectable} from "@angular/core";
import {User} from "./user.model";
import {RequestOptions, Headers, Http} from "@angular/http";
import {ServerAddressService} from "../shared/server-address.service";

@Injectable()
export class CustomLoginService {
    private user: User;
    private isAuthenticated: boolean;

    constructor(private http : Http, private serverAddressService : ServerAddressService) {
        this.isAuthenticated = false
    };

    authenticate(username: string, password: string) {
        const url = this.serverAddressService.serverAddress + '/api/users/profile';
        let options = new RequestOptions({
            headers: CustomLoginService.createAuthHeader(username, password),
            withCredentials: CustomLoginService.isUserNotLoggedIn()
        });
        return this.http.get(url, options);
    }

    static createAuthHeader(username: string, password: string): Headers {
        return new Headers({
            'authorization': 'Basic '
            + btoa(username + ':' + password)
        });
    }

    private static isUserNotLoggedIn() : boolean {
        var currentUser = localStorage.getItem("currentUser");
        console.log('isUserLoggedIn ' +JSON.stringify(currentUser));
        return currentUser == null;
    }

    static isUserLoggedIn() : boolean {
        return localStorage.getItem("currentUser") != null;
    }
}