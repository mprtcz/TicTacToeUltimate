import {Injectable} from "@angular/core";
import {Http, RequestOptions} from "@angular/http";
import {User} from "../login/user.model";
import {CurrentUserService} from "../shared/current-user.service";
import {ServerAddressService} from "../shared/server-address.service";

@Injectable()
export class UsersService {

    constructor(private http: Http, private currentUserService: CurrentUserService, private serverAddressService : ServerAddressService) {}

    getUsers(): Promise {
        let url : string = this.serverAddressService.serverAddress + '/api/users';
        return this.http.get(url, this.getOptions()).toPromise();
    }

    deleteUser(user : User) : Promise {
        let userSooId : string = user.ssoId;
        let url : string = this.serverAddressService.serverAddress + '/api/users/' + userSooId;
        return this.http.delete(url, this.getOptions()).toPromise();
    }

    getOptions() : RequestOptions {
        return new RequestOptions({ withCredentials: this.currentUserService.isUserLoggedIn() });
    }
}