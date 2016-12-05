
import {Injectable} from "@angular/core";
import {User} from "../login/user";
@Injectable()
export class EditUserService {
    user : User;

    setUser(user : User) : void {
        console.log('setting user: '+JSON.stringify(user));
        this.user = user;
    }

    getUser() : User {
        console.log('returning user: '+JSON.stringify(this.user));
        return this.user;
    }
}