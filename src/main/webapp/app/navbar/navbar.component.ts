import {Component, AfterContentChecked, OnInit} from "@angular/core";
import {CustomLoginService} from "../login/custom-login.service";
import {User} from "../login/user";

declare var componentHandler: any;

@Component({
    moduleId: module.id,
    selector: 'app-navbar',
    templateUrl: '/app/navbar/navbar.component.jsp',
    providers: [CustomLoginService],
})
export class NavbarComponent implements AfterContentChecked {

    ngAfterContentChecked(): void {
        this.user = JSON.parse(localStorage.getItem("currentUser"));
    }

    private user: User;

    constructor() {
        this.user = JSON.parse(localStorage.getItem("currentUser"));
    }
}