import {Component} from "@angular/core";
import {User} from "../login/user.model";
import {UserService} from "./user.service";
import {RegisterService} from "../register/register.service";
import {EditUserService} from "../shared/edit-user.service";

@Component({
    moduleId: module.id,
    selector: 'user-info',
    templateUrl: '/app/user/user.component.jsp',
    providers: [
        UserService,
        RegisterService
    ]
})
export class UserComponent {
    private user: User;
    private message: string;
    private isDisplaying: boolean;

    constructor(private userService: UserService,
                private editUserService: EditUserService) {
        this.user = JSON.parse(localStorage.getItem("currentUser"));
    }

    deleteAccount() {
        this.userService.deleteAccount().toPromise()
            .then(res => {
                localStorage.removeItem("currentUser");
                console.log(JSON.stringify(res));
                this.user = '';
                this.message = 'User Removed';
            })
            .catch((error: any) => {
                this.message = JSON.stringify(error);
            })
    }

    setUser() : void {
        this.editUserService.setUser(this.user);
        console.log('setuser: ' + JSON.stringify(this.user));
    }

    setUser(user : User) : void {
        this.editUserService.setUser(user);
        console.log('setuser: ' + JSON.stringify(this.user));
    }

    showGamesHistory() : void {

    }
}