import {Component} from "@angular/core";
import {EditUserService} from "../shared/edit-user.service";

@Component({
    moduleId: module.id,
    selector: 'app-logout',
    templateUrl: './logout.component.jsp'
})
export class LogoutComponent {

    constructor(private sharedService: EditUserService) {
        localStorage.removeItem("currentUser");
        this.sharedService.setUser(null);
    }

    getCurrentUser() {
        return JSON.parse(localStorage.getItem("currentUser"));
    }
}