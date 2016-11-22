import {Component} from "@angular/core";

@Component({
    moduleId: module.id,
    selector: 'app-logout',
    templateUrl: './logout.component.jsp'
})
export class LogoutComponent {

    constructor() {
        localStorage.removeItem("currentUser")
    }

    getCurrentUser() {
        return JSON.parse(localStorage.getItem("currentUser"));
    }
}