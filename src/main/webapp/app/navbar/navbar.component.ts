import {Component, AfterContentChecked, OnInit} from "@angular/core";
import {CustomLoginService} from "../login/custom-login.service";
import {User} from "../login/user.model";
import {CurrentUserService} from "../shared/current-user.service";

declare var componentHandler: any;

@Component({
    moduleId: module.id,
    selector: 'app-navbar',
    templateUrl: '/app/navbar/navbar.component.jsp',
    providers: [CustomLoginService],
})
export class NavbarComponent implements AfterContentChecked, OnInit {

    ngOnInit(): void {
        this.currentUserService.checkIfTheUserIsLoggedIn()
            .then(res => {
                console.log('User logged in');
            })
            .catch((error: any) => {
                if (error.status == 401) {
                    console.log('nullyfying the user');
                    this.user = null;
                    this.currentUserService.nullifyLoggedInUser();
                }
            });
    }

    ngAfterContentChecked(): void {
        this.user = this.currentUserService.getCurrentLoggedInUser();
    }

    private user: User;

    constructor(private customLoginService: CustomLoginService, private currentUserService: CurrentUserService) {
        this.user = this.currentUserService.getCurrentLoggedInUser();
    }
}