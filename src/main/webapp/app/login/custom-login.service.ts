import {Injectable} from "@angular/core";
import {User} from "./user";
import {RequestOptions, Headers, Http} from "@angular/http";
import {toPromise} from "rxjs/operator/toPromise";

@Injectable()
export class CustomLoginService {
    private user: User;
    private isAuthenticated: boolean;

    constructor(private http : Http) {
        this.isAuthenticated = false
    };

    authenticate(username: string, password: string) {
        const url = 'http://localhost:8080/profile';
        let options = new RequestOptions({headers: CustomLoginService.createAuthHeader(username, password),
            withCredentials: CustomLoginService.isUserNotLoggedIn()});
        return this.http.get(url, options);
    }

    static createAuthHeader(username: string, password: string): string {
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