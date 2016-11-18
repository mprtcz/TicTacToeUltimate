import {Component} from "@angular/core";
import {Http, RequestOptions, Headers, Response} from "@angular/http";
import {Router} from "@angular/router";
import {CustomLoginService} from "./custom-login.service";
import {Observable}     from 'rxjs/Observable';
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

    getData(): void {
        this.message = this.loginService.authenticate(this.username, this.password);
        console.log('message = ' + this.message)
    }
}