import {Injectable} from "@angular/core";
import {NewUser} from "./NewUser";
import {Http} from "@angular/http";
import {User} from "../login/user";

@Injectable()
export class RegisterService {

    constructor(private http : Http) {}

    registerNewUser(newUser : NewUser) {
        const address = 'http://localhost:8080/user/add';
        return this.http.post(address, newUser);
    }
}