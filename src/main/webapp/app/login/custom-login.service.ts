import {Injectable} from "@angular/core";
import {User} from "./user";
import {RequestOptions, Headers, Http} from "@angular/http";
import {isUndefined} from "util";

@Injectable()
export class CustomLoginService {
    private user: User;
    private isAuthenticated: boolean;

    constructor(private http : Http) {
        this.isAuthenticated = false
    };

    authenticate(username: string, password: string): Promise<boolean> {
        this.user = null;
        this.isAuthenticated = null;
        localStorage.removeItem("currentUser");
        const url = 'http://localhost:8080/user';
        let options = new RequestOptions({headers: CustomLoginService.createAuthHeader(username, password),
            withCredentials: CustomLoginService.isUserNotLoggedIn()});
        this.http.get(url, options)
            .toPromise()
            .then(res => {
                this.user = res.json() as User;
                localStorage.setItem("currentUser", JSON.stringify(this.user));
                console.log("Added user: "+JSON.stringify(localStorage.getItem("currentUser")));
                this.isAuthenticated = true;
            })
            .catch((error: any) => {
                this.isAuthenticated = false;
                if (error.status === 401) {
                    console.log("Error 401")
                } else {
                    console.log("Error " +error.status);
                }
                console.log('error occured!');
            });
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