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
        this.message = this.loginService.authenticate(this.username, this.password);
        console.log('message = ' + this.message)
    }
}