import {Injectable} from "@angular/core";
import {NewUser} from "./new-user.model";
import {Http, RequestOptions} from "@angular/http";
import {User} from "../login/user.model";
import {ServerAddressService} from "../shared/server-address.service";

@Injectable()
export class RegisterService {

    constructor(private http : Http, private serverAddressService : ServerAddressService) {}

    registerNewUser(newUser : NewUser) {
        const address = this.serverAddressService.serverAddress + '/api/users/add';
        return this.http.post(address, newUser);
    }

    updateUser(newUser : NewUser) {
        let options = new RequestOptions({ withCredentials: true });
        const address = this.serverAddressService.serverAddress + '/api/users/profile';
        return this.http.patch(address, newUser, options);
    }
}