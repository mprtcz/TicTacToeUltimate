import {Injectable} from '@angular/core';
import {Http} from "@angular/http";

import 'rxjs/add/operator/toPromise';

@Injectable()
export class GreetingService {

    private greetingUrl = '/api/hello';

    constructor(private http: Http) { }

    getSimpleGreetingFromServer() : Promise<string> {
        return this.http.get(this.greetingUrl)
            .toPromise()
            .then(response => response.text() as string)
            .catch(this.handleError);
    }

    getMockMessage() : Promise<string> {
        return 'mockMessage';
    }

}