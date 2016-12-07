import {
    Component, AfterViewChecked, AfterContentChecked, OnInit, AfterViewInit,
    ViewEncapsulation, OnChanges, SimpleChanges
} from "@angular/core";
import {CustomLoginService} from "../login/custom-login.service";
import {User} from "../login/user";

declare var componentHandler: any;

@Component({
    moduleId: module.id,
    selector: 'app-navbar',
    templateUrl: './navbar.component.jsp',
    providers: [CustomLoginService],
})
export class NavbarComponent implements AfterContentChecked {

    ngAfterContentChecked(): void {
        this.user = JSON.parse(localStorage.getItem("currentUser"));
        componentHandler.upgradeDom();
    }

    private user: User;

    constructor() {
        this.user = JSON.parse(localStorage.getItem("currentUser"));
    }

    private determineUser(): string {
        this.user = JSON.parse(localStorage.getItem("currentUser"));
        if (this.user != null) {
            return this.user.nickname.replace(new RegExp('\"', 'g'), ''); //new regexp required in order to remove all " characters in given string
        } else {
            return "Guest";
        }
    }
}