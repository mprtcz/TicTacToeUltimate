
import {Injectable} from "@angular/core";
import {User} from "../login/user.model";
import {NewUser} from "../register/new-user.model";
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

    getNewUser(): NewUser {
        if(this.user == null) {return null}
        let newUser = new NewUser();
        newUser.nickname = this.user.nickname;
        newUser.email = this.user.email;
        newUser.ssoId = this.user.ssoId;
        newUser.role = this.user.role;
        return newUser;
    }
}