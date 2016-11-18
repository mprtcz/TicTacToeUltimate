import {Component} from "@angular/core";
import {Http, RequestOptions, Headers, Response} from "@angular/http";
import {Router} from "@angular/router";
import {CustomLoginService} from "./custom-login.service";
import { Observable }     from 'rxjs/Observable';
import 'rxjs/Rx';
import {User} from "./user";

@Component({
    moduleId: module.id,
    selector: 'custom-login',
    templateUrl: './custom-login.component.jsp'
})
export class CustomLoginComponent {
    private username: string;
    private password: string;
    private message: string;

    constructor(private http: Http,
                private router: Router,
                private loginService: CustomLoginService) {
    }

    submit(): Promise<User> {
        const url = 'http://localhost:8080/user';
        let options = new RequestOptions({headers: this.createAuthHeader(), withCredentials: true});
        return this.http.get(url, options)
            .toPromise()
            .then(response => response.json().data as User,
                this.message = 'Success',
                this.loginService.setUser('user'))
            .catch((error: any) => {
                if (error.status === 401) {
                    this.message = 'Bad credentials'
                }
            });
    }

    getData() : void {
        let response = this.submit();
        console.log('Response = ' +JSON.stringify(response));
    }

    getText(): User {
        const url = 'http://localhost:8080/user';
        let options = new RequestOptions({headers: this.createAuthHeader(), withCredentials: true});
        return this.http.get(url, options)
            .map(res => res.json().data as User);
    }

    createAuthHeader(): string {
        return new Headers({
            'authorization': 'Basic '
            + btoa(this.username + ':' + this.password)
        });
    }
}