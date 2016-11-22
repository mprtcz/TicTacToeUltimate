import {Component} from "@angular/core";
import {User} from "../login/user";

@Component({
    moduleId: module.id,
    selector: 'user-info',
    templateUrl: './user.component.jsp'
})
export class UserComponent {
    private user : User;

    constructor() {
        this.user = JSON.parse(localStorage.getItem("currentUser"));
    }
}