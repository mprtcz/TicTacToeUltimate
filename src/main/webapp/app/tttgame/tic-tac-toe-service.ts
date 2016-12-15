import { Injectable } from '@angular/core';
import {Http, RequestOptions, Headers} from "@angular/http";

@Injectable()
export class TicTacToeService {

    constructor(private http: Http){}

    createGame() : Promise {
        console.log('creating game');
        let url = 'http://localhost:8080/tictactoe/create';
        let options = new RequestOptions({ withCredentials: localStorage.getItem("currentUser") });
        return this.http.get(url, options).toPromise();
    }

    gameState(gameHost: string) : Promise {
        console.log('updating game');
        let url = 'http://localhost:8080/tictactoe/' + gameHost + '/game';
        let options = new RequestOptions({ withCredentials: localStorage.getItem("currentUser") });
        return this.http.get(url, options).toPromise();
    }

    parseAndSendInsertion(number: number) {
        let firstCoordinate = Math.floor(number/3);
        let secondCoordinate = number % 3;
        let resultedString = firstCoordinate + ',' + secondCoordinate;
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({ withCredentials: true , headers: headers});
        console.log('Request options: ' +JSON.stringify(options));
        let url = 'http://localhost:8080/tictactoe/move';
        console.log('move coodrinates: ' +resultedString);
        return this.http.patch(url, resultedString, options).toPromise();
    }

}