import { Injectable } from '@angular/core';
import {Http, RequestOptions, Response} from "@angular/http";
import {TicTacToeDTO} from "../tttgame/tic-tac-toe-dto.model";
import {CurrentUserService} from "../shared/current-user.service";
import 'rxjs/add/operator/toPromise';

@Injectable()
export class GamesService {

    constructor(private http: Http, private currentUserService: CurrentUserService) { }

    fetchTTTGamesFromServer() : Promise {
        let options = new RequestOptions({ withCredentials: this.currentUserService.isUserLoggedIn() });
        const url = 'http://localhost:8080/games/';
        return this.http.get(url, options).toPromise();
    }

    joinGame(game : TicTacToeDTO) : Promise {
        let options = new RequestOptions({ withCredentials: this.currentUserService.isUserLoggedIn() });
        const url = 'http://localhost:8080/tictactoe/' + game.gameHost + '/join';
        return this.http.get(url, options).toPromise();
    }
}