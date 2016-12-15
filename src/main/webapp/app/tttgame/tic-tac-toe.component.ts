import {Component, OnInit, OnDestroy} from "@angular/core";
import {TicTacToeGame} from "./tictactoegame.model";
import {TicTacToeService} from "./tic-tac-toe-service";
import {Observable} from "rxjs/Observable";

@Component({
    moduleId: module.id,
    selector: 'tttgame',
    templateUrl: '/app/tttgame/tic-tac-toe.component.jsp',
    providers: [TicTacToeService]
})
export class TicTacToeComponent implements OnInit, OnDestroy {

    ngOnDestroy(): void {
        console.log('Destroying component!');
        clearInterval(this.timer);
        this.subscription.unsubscribe();
    }

    private game: TicTacToeGame;
    private currentSymbol: string = 'X';
    private gameHost: string;
    private subscription: any;
    private secondPlayerUsername: string;

    ngOnInit(): void {
        console.log('ngOnInit ttt');
        this.game = new TicTacToeGame();
        this.tttService.createGame()
            .then(response => {
                console.log('response: ' + JSON.stringify(response));
                this.isGameOn = true;
                this.gameHost = JSON.parse(localStorage.getItem("currentUser")).ssoId;
                console.log('gameHost: ' + this.gameHost);
            })
            .catch((error: any) => {
                console.log('error: ' + JSON.stringify(error));
            });
        let timer = Observable.timer(1000, 1000);
        this.subscription = timer.subscribe(t=> {
            this.ticks = t;
            if (this.secondPlayerUsername) {
                this.getGamesState()
            } else {
                this.checkIfSomeoneJoinedGame();
            }
        });
    }

    constructor(private tttService: TicTacToeService) {
        console.log('constr ttt');
        this.isGameOn = false;
    }

    private isGameOn: boolean;

    insertSymbol(index: int): void {
        console.log('insert symbol: ' + index);
        if (this.game.symbols[index] == ' ') {
            this.game.symbols[index] = this.currentSymbol;
            this.reverseSymbol();
        }
    }

    clear(): void {
        this.game = new TicTacToeGame();
        this.getGamesState();
    }

    reverseSymbol(): void {
        if (this.currentSymbol == 'X') {
            this.currentSymbol = 'O';
        } else {
            this.currentSymbol = 'X';
        }
    }

    getGamesState(): void {
        this.tttService.gameState(this.gameHost)
            .then(response => {
                console.log('response: ' + JSON.stringify(response._body));
            })
            .catch((error: any) => {
                console.log('error: ' + JSON.stringify(error));
                console.log('error: ' + JSON.stringify(error.stack));
            });
    }

    checkIfSomeoneJoinedGame(): void {
        this.tttService.gameState(this.gameHost)
            .then(response => {
                console.log('response: ' + JSON.stringify(response._body));
                let game = JSON.parse(response._body);
                console.log('Game\'s second player: ' +JSON.stringify(game.secondPlayer));
                console.log('Game host: ' +JSON.stringify(game.gameHost));
                console.log('table: ' +JSON.stringify(game.oneDimTable));
                console.log('current player: ' +JSON.stringify(game.currentPlayer));
                if(game.secondPlayer != null) {
                    this.secondPlayerUsername = game.secondPlayer;
                }
            })
            .catch((error: any) => {
                console.log('error: ' + JSON.stringify(error));
                console.log('error: ' + JSON.stringify(error.stack));
            });
    }

}