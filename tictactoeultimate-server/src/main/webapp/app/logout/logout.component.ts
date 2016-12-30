import {Component, OnInit} from "@angular/core";
import {EditUserService} from "../shared/edit-user.service";
import {Http} from "@angular/http";
import {ServerAddressService} from "../shared/server-address.service";

@Component({
    moduleId: module.id,
    selector: 'app-logout',
    templateUrl: '/app/logout/logout.component.jsp'
})
export class LogoutComponent implements OnInit {

    ngOnInit(): void {
        let address : String = this.serverAddressService.serverAddress + '/logout';
        console.log('oninit2 logout');
        this.http.get(address).toPromise()
            .then(res => {
                console.log(JSON.stringify(res));
            }).catch((error :any)=> {
            console.log('error ' +JSON.stringify(error));
        });
        this.sharedService.setUser(null);
    }

    constructor(private sharedService: EditUserService, private http: Http, private serverAddressService : ServerAddressService) {
        localStorage.removeItem("currentUser");
    }

    getCurrentUser() {
        return JSON.parse(localStorage.getItem("currentUser"));
    }
}