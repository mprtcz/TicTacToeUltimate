import {Injectable} from "@angular/core";
import {Http, RequestOptions} from "@angular/http";
import {CustomLoginService} from "../login/custom-login.service";


@Injectable()
export class UserService {

    constructor(private http: Http){};

    deleteAccount() {
        const url = 'http://localhost:8080/users/profile';
        let options = new RequestOptions({withCredentials: CustomLoginService.isUserLoggedIn()});
        return this.http.delete(url, options);
    }
}