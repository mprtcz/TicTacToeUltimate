import {Injectable} from "@angular/core";
import {NewUser} from "./NewUser";
import {Http} from "@angular/http";

@Injectable()
export class RegisterService{

    constructor(private http : Http) {}

    registerNewUser(newUser : NewUser) {
        const address = 'http://localhost:8080/user/add';
        return this.http.post(address, newUser);
    }
}