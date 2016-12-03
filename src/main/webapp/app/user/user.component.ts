import {Component} from "@angular/core";
import {User} from "../login/user";
import {UserService} from "./user.service";
import {error} from "util";

@Component({
    moduleId: module.id,
    selector: 'user-info',
    templateUrl: './user.component.jsp',
    providers: [UserService]
})
export class UserComponent {
    private user: User;
    private message: string;

    constructor(private userService: UserService) {
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
}