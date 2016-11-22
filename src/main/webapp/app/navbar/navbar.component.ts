import {Component} from "@angular/core";
import {CustomLoginService} from "../login/custom-login.service";
import {User} from "../login/user";

@Component({
    moduleId: module.id,
    selector: 'app-navbar',
    templateUrl: './navbar.component.jsp'
})
export class NavbarComponent {
    private user : User;

    constructor(private loginService: CustomLoginService) {
        this.user = JSON.parse(localStorage.getItem("currentUser"));
    }

    isAnonymous() : boolean {
        return this.loginService.getUser() === null;
    }

    getUser() : string {
        this.user = JSON.parse(localStorage.getItem("currentUser"));
        if(this.user != null){
            return this.user.nickname.replace(new RegExp('\"', 'g'), ''); //new regexp required in order to remove all " characters in given string
        } else {
            return "Guest";
        }
    }
}