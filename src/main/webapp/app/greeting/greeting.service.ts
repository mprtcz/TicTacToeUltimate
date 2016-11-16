import {Injectable} from '@angular/core';
import {Http} from "@angular/http";

import 'rxjs/add/operator/toPromise';
import {Router} from "@angular/router";
import {CredentialsService} from "../credentials/credentials.service";

@Injectable()
export class GreetingService {

    private greetingUrl = 'http://localhost:8080/api/hello';

    constructor(private http: Http,
                private router: Router,
                private credentialsService: CredentialsService) {
    }

    getSimpleGreetingFromServer(): Promise<string> {
        return this.http.get(this.greetingUrl)
            .toPromise()
            .then(response => response.text() as string)
            .catch(this.handleError);
    }

    getPersonalizedMessageFromServer(variable: string): Promise<string> {
        const url: string = this.greetingUrl + '/' + variable;
        return this.http.get(url, {headers: this.credentialsService.getHeader()})
            .toPromise()
            .then(response => response.text() as string)
            .catch((error: any) => {
                if (error.status === 401) {
                    console.log(error.status);
                    this.router.navigate(['/login'])
                }
            });
    }
}