import {Component, OnInit, EventEmitter} from '@angular/core';
import {TicTacToeDTO} from "../tttgame/tic-tac-toe-dto.model";
import {GamesService} from "./games.service";
import {Router} from "@angular/router";
import {Output} from "@angular/core/src/metadata/directives";

@Component({
    moduleId: module.id,
    selector: 'games-list',
    templateUrl: '/app/games/games-list.component.jsp',
    providers: [GamesService]
})
export class GamesListComponent implements OnInit {
    private tttGames: TicTacToeDTO[];

    @Output() notifyParent : EventEmitter<any> = new EventEmitter();

    constructor(private gamesService: GamesService) {
    }

    ngOnInit() {
        console.log('initing gameslist');
        this.gamesService.fetchTTTGamesFromServer()
            .then(resp => {
                console.log(JSON.stringify(resp));
                this.tttGames = JSON.parse(resp._body);
                console.log('games: ' + JSON.stringify(this.tttGames));
            }).catch(function (error) {
            console.log("Error " + JSON.stringify(error));
        });
    }

    joinGame(game: TicTacToeDTO): void {
        this.gamesService.joinGame(game)
            .then(resp => {
                console.log(JSON.stringify(resp));
                this.notifyParent.emit(true);
            }).catch(function (error) {
            console.log("Error " + JSON.stringify(error));
        });
    }

}