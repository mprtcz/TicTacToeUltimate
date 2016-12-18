import {Injectable} from "@angular/core";
import {NewUser} from "./new-user.model";
import {Http, RequestOptions} from "@angular/http";
import {User} from "../login/user.model";

@Injectable()
export class RegisterService {

    constructor(private http : Http) {}

    registerNewUser(newUser : NewUser) {
        const address = 'http://localhost:8080/api/users/add';
        return this.http.post(address, newUser);
    }

    updateUser(newUser : NewUser) {
        let options = new RequestOptions({ withCredentials: true });
        const address = 'http://localhost:8080/api/users/profile';
        return this.http.patch(address, newUser, options);
    }
}