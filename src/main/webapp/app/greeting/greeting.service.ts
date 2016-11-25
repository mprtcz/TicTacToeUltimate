import {Injectable} from '@angular/core';
import {Http, RequestOptions} from "@angular/http";

import 'rxjs/add/operator/toPromise';
import {Router} from "@angular/router";

@Injectable()
export class GreetingService {

    private greetingUrl = 'http://localhost:8080/api/hello';

    constructor(private http: Http,
                private router: Router) {}

    getSimpleGreetingFromServer(): Promise<string> {
        return this.http.get(this.greetingUrl)
            .toPromise()
            .then(response => response.text() as string)
            .catch(this.handleError);
    }

    getPersonalizedMessageFromServer(variable: string): Promise<string> {
        let options = new RequestOptions({ withCredentials: localStorage.getItem("currentUser") });
        //let options = new RequestOptions({ withCredentials: true });
        console.log('options ' +JSON.stringify(options));
        const url: string = this.greetingUrl + '/' + variable;
        return this.http.get(url, options)
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