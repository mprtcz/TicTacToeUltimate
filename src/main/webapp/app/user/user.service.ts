import {Injectable} from "@angular/core";
import {Http, RequestOptions} from "@angular/http";
import {CustomLoginService} from "../login/custom-login.service";
import {ServerAddressService} from "../shared/server-address.service";

@Injectable()
export class UserService {

    constructor(private http: Http, private private serverAddressService : ServerAddressService){};

    deleteAccount() {
        const url = this.serverAddressService.serverAddress + '/api/users/profile';
        let options = new RequestOptions({withCredentials: CustomLoginService.isUserLoggedIn()});
        return this.http.delete(url, options);
    }
}