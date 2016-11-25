import {Component} from "@angular/core";
import {CustomLoginService} from "./custom-login.service";
import 'rxjs/Rx';

@Component({
    moduleId: module.id,
    selector: 'custom-login',
    templateUrl: './custom-login.component.jsp'
})
export class CustomLoginComponent {
    private username: string;
    private password: string;
    private message: string;

    constructor(private loginService: CustomLoginService) {
    }

    getData(): void {
        var isLoggedIn = this.loginService.authenticate(this.username, this.password);
        if (isLoggedIn != null) {
            if (isLoggedIn) {
                this.message = 'Logged In'
            } else {
                this.message = 'Bad Credentials'
            }
        }
    }
}