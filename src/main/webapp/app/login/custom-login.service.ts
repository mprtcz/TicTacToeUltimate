import {Injectable} from "@angular/core";
import {User} from "./user";
import {RequestOptions, Headers, Http} from "@angular/http";

@Injectable()
export class CustomLoginService {
    private user: User;
    private isAuthenticated: boolean;

    constructor(private http : Http) {
        this.isAuthenticated = false
    };

    authenticate(username: string, password: string): void {
        this.user = null;
        localStorage.removeItem("currentUser");
        const url = 'http://localhost:8080/user';
        let options = new RequestOptions({headers: CustomLoginService.createAuthHeader(username, password)/*, withCredentials: true*/});
        this.http.get(url, options)
            .toPromise()
            .then(res => {
                this.user = res.json() as User;
                localStorage.setItem("currentUser", JSON.stringify(this.user));
                console.log("Added user: "+JSON.stringify(localStorage.getItem("currentUser")))
            })
            .catch((error: any) => {
                if (error.status === 401) {
                    console.log("Error 401")
                } else {
                    console.log("Error " +error.status);
                }
            });
    }

    static createAuthHeader(username: string, password: string): string {
        return new Headers({
            'authorization': 'Basic '
            + btoa(username + ':' + password)
        });
    }
}