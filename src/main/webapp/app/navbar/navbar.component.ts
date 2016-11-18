import {Component} from "@angular/core";
import {CustomLoginService} from "../login/custom-login.service";
import {User} from "../login/user";

@Component({
    moduleId: module.id,
    selector: 'app-navbar',
    templateUrl: './navbar.component.jsp'
})
export class NavbarComponent {
    private  loggedInUser = this.loginService.getUser();

    constructor(private loginService: CustomLoginService) {
        this.loggedInUser = this.loginService.getUser();
    }

    isAnonymous() : boolean {
        return this.loginService.getUser() === null;
    }

    getUser() : User {
        return this.loginService.getUser();
    }
}