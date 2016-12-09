import {Injectable} from "@angular/core";
import {Http, RequestOptions} from "@angular/http";
import {User} from "../login/user";

@Injectable()
export class UsersService {

    constructor(private http: Http) {}

    getUsers(): Promise {
        let url : string = 'http://localhost:8080/users';
        let options = new RequestOptions({ withCredentials: true });
        return this.http.get(url, options).toPromise();
    }

    deleteUser(user : User) : Promise {
        let userSooId : string = user.ssoId;
        let url : string = 'http://localhost:8080/users/' + userSooId;
        let options = new RequestOptions({ withCredentials: true });
        return this.http.delete(url, options).toPromise();
    }
}