import {Component, OnInit} from "@angular/core";
import {EditUserService} from "../shared/edit-user.service";
import {Http} from "@angular/http";

@Component({
    moduleId: module.id,
    selector: 'app-logout',
    templateUrl: './logout.component.jsp'
})
export class LogoutComponent implements OnInit {

    ngOnInit(): void {
        let address : String = 'http://localhost:8080/logout';
        console.log('oninit2 logout');
        this.http.get(address).toPromise()
            .then(res => {
                console.log(JSON.stringify(res));
            }).catch((error :any)=> {
            console.log('error ' +JSON.stringify(error));
        });
        this.sharedService.setUser(null);
    }

    constructor(private sharedService: EditUserService, private http: Http) {
        localStorage.removeItem("currentUser");
    }

    getCurrentUser() {
        return JSON.parse(localStorage.getItem("currentUser"));
    }
}