import {Component} from "@angular/core";
import {Http} from "@angular/http";
import {CredentialsService} from "../credentials/credentials.service";

@Component({
    moduleId: module.id,
    selector: 'custom-login',
    templateUrl: './custom-login.component.jsp'
})
export class CustomLoginComponent {
    private username: string;
    private password: string;
    private response: Promise<>;

    constructor(private http: Http,
                private credentialsService: CredentialsService) {
    }

    submit(): Promise<> {
        this.credentialsService.createAuthHeader(this.username, this.password);
        const url = 'http://localhost:8080/user';
        return this.http.get(url, {headers: this.credentialsService.getHeader()})
            .toPromise()
            .then()
            .catch((error: any) => {
                if (error.status === 401) {
                    this.credentialsService.clearCredentials();
                    this.router.navigate(['/login'])
                }
            });
    }

    getResponse(): void {
        this.response = JSON.stringify(this.submit());
        console.log('Response:' + this.response);
    }
}