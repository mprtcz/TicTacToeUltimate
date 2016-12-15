import {Injectable} from '@angular/core';
import {User} from "../login/user.model";
import {Http, RequestOptions, Response} from "@angular/http";

@Injectable()
export class CurrentUserService {

    constructor(private http : Http) {
    }

    isUserLoggedIn(): boolean {
        let user = JSON.parse(localStorage.getItem("currentUser"));
        console.log('loggedInuser: ' +JSON.stringify(user));
        return !!user;
    }

    getLoggedUserSsoId() : string {
        let user = JSON.parse(localStorage.getItem("currentUser"));
        if(user) {
            return user.ssoId;
        } else {
            return 'anonymousUser';
        }
    }

    getCurrentLoggedInUser() : User {
        let user = JSON.parse(localStorage.getItem("currentUser"));
        if(user) {
            return user;
        } else {
            return null;
        }
    }

    nullifyLoggedInUser() : void {
        localStorage.removeItem("currentUser");
    }

    checkIfTheUserIsLoggedIn() : Promise {
        let url = 'http://localhost:8080/api/principal';
        let options = new RequestOptions({ withCredentials: true });
        return this.http.get(url, options).toPromise()
    }
}