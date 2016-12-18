import {Injectable} from "@angular/core";
import {Http, RequestOptions} from "@angular/http";
import {User} from "../login/user.model";
import {CurrentUserService} from "../shared/current-user.service";

@Injectable()
export class UsersService {

    constructor(private http: Http, private currentUserService: CurrentUserService) {}

    getUsers(): Promise {
        let url : string = 'http://localhost:8080/api/users';
        return this.http.get(url, this.getOptions()).toPromise();
    }

    deleteUser(user : User) : Promise {
        let userSooId : string = user.ssoId;
        let url : string = 'http://localhost:8080/api/users/' + userSooId;
        return this.http.delete(url, this.getOptions()).toPromise();
    }

    getOptions() : RequestOptions {
        return new RequestOptions({ withCredentials: this.currentUserService.isUserLoggedIn() });
    }
}