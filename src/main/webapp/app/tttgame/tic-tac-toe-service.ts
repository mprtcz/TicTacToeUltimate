import { Injectable } from '@angular/core';
import {Http, RequestOptions} from "@angular/http";

@Injectable()
export class TicTacToeService {

    constructor(private http: Http){}

    createGame() : Promise {
        console.log('creating game');
        let url = 'http://localhost:8080/tictactoe/create';
        let options = new RequestOptions({ withCredentials: localStorage.getItem("currentUser") });
        return this.http.get(url, options).toPromise();
    }

}