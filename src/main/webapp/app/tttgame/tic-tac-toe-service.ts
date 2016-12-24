import { Injectable } from '@angular/core';
import {Http, RequestOptions, Headers} from "@angular/http";
import {ServerAddressService} from "../shared/server-address.service";

@Injectable()
export class TicTacToeService {

    constructor(private http: Http, private serverAddressService : ServerAddressService){}

    createGame() : Promise {
        console.log('creating game');
        let url = this.serverAddressService.serverAddress + '/tictactoe/create';
        let options = new RequestOptions({ withCredentials: localStorage.getItem("currentUser") });
        return this.http.get(url, options).toPromise();
    }

    gameState(gameHost: string) : Promise {
        console.log('updating game');
        let url = this.serverAddressService.serverAddress + '/tictactoe/' + gameHost + '/game';
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
        let url = this.serverAddressService.serverAddress + '/tictactoe/move';
        console.log('move coodrinates: ' +resultedString);
        return this.http.patch(url, resultedString, options).toPromise();
    }

}