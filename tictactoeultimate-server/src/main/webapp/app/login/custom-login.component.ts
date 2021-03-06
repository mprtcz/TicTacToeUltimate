import  {Component } from "@angular/core";
import {CustomLoginService} from "./custom-login.service";
import 'rxjs/Rx';
import {User} from "./user.model";
import {Router} from "@angular/router";
declare var componentHandler: any;

@Component({
    moduleId: module.id,
    selector: 'custom-login',
    templateUrl: '/app/login/custom-login.component.jsp',
    providers: [CustomLoginService]
})
export class CustomLoginComponent {

    private username: string;
    private password: string;
    private message: string;
    private user: User;

    constructor(private loginService: CustomLoginService,
    private router: Router) {
    }

    getData(): void {
        localStorage.removeItem("currentUser");
        this.loginService.authenticate(this.username, this.password).toPromise()
            .then(res => {
                console.log("Response: " +JSON.stringify(res));
                this.user = res.json() as User;
                console.log("Added this.user: " + JSON.stringify(this.user));
                localStorage.setItem("currentUser", JSON.stringify(this.user));
                console.log("Added user: " + JSON.stringify(localStorage.getItem("currentUser")));
                this.message = 'Success';
                this.router.navigate(['/summary']);
            })
            .catch((error: any) => {
                if (error.status === 401) {
                    console.log("Error 401")
                } else {
                    console.log("Error " + error.status);
                }
                console.log('error occurred!');
                this.message = 'Bad Credentials';
            });
    }
}