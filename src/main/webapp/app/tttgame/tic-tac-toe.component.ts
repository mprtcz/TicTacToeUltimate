import {Component, OnInit, OnDestroy} from "@angular/core";
import {TicTacToeGameArray} from "./tictactoegame.model";
import {TicTacToeService} from "./tic-tac-toe-service";
import {Observable} from "rxjs/Observable";
import {TicTacToeDTO} from "./tic-tac-toe-dto.model";
import {Router} from "@angular/router";
import {GameDataholderService} from "../shared/game-datahoder.service";

@Component({
    moduleId: module.id,
    selector: 'tttgame',
    templateUrl: '/app/tttgame/tic-tac-toe.component.jsp',
    providers: [
        TicTacToeService
    ]
})
export class TicTacToeComponent implements OnInit, OnDestroy {

    ngOnDestroy(): void {
        console.log('Destroying component!');
        clearInterval(this.timer);
        this.subscription.unsubscribe();
    }

    private game: TicTacToeGameArray;
    private gameHost: string;
    private loggedPlayer: string;
    private subscription: any;
    private isSecondPlayerInGame: boolean;
    private gameDto : TicTacToeDTO;
    private playersSign : string;

    ngOnInit(): void {
        console.log('ngOnInit ttt');
        this.game = new TicTacToeGameArray();
        this.loggedPlayer = JSON.parse(localStorage.getItem("currentUser")).ssoId;

        if(this.gameDataholderService.isCreating) {
            console.log('isCreating: true');
            this.createGame()
        } else {
            console.log('isCreating: false');
            this.gameHost = this.gameDataholderService.gameHost;
            this.getGamesState();
            this.setUpSubscriptionTimer();
        }

    }

    setUpSubscriptionTimer() : void {
        let timer = Observable.timer(1000, 1000);
        this.subscription = timer.subscribe(t=> {
            this.ticks = t;
            if (this.gameDto.secondPlayer) {
                this.getGamesState();
            } else {
                this.checkIfSomeoneJoinedGame();
            }
        });
    }

    constructor(private tttService: TicTacToeService,
                private router : Router,
                private gameDataholderService : GameDataholderService) {
        console.log('constr ttt');
        this.isGameOn = false;
    }

    private isGameOn: boolean;

    insertSymbol(index: int): void {
        console.log('insert symbol: ' + index);
        if (this.game.symbols[index] == ' ') {
            this.game.symbols[index] = this.playersSign;
            this.reverseSymbol();
            this.tttService.parseAndSendInsertion(index)
                .then(response => {
                    this.updateGameDto(response);
                    console.log('response: ' + JSON.stringify(response._body));
                })
                .catch((error: any) => {
                    console.log('error: ' + JSON.stringify(error));
                    console.log('error: ' + JSON.stringify(error.stack));
                });
        }
    }

    clear(): void {
        this.router.navigate(['/greeting']);
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
                this.updateGameDto(response);
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
                this.updateGameDto(response);
            })
            .catch((error: any) => {
                if(error.status === 500) {
                    console.log('Server Error!');
                }
            });
    }

    determineIfThePlayerHasMove() : boolean {
        if(this.loggedPlayer == this.gameDto.gameHost) {
            return this.gameDto.currentPlayer == 'O';
        } else {
            return this.gameDto.currentPlayer == 'X';
        }
    }

    updateGameDto(response : string) : void {
        this.gameDto = JSON.parse(response._body);
        this.isSecondPlayerInGame = this.gameDto.secondPlayer != null;
        this.game.symbols = this.gameDto.oneDimTable;
    }

    createGame() : void {
        this.tttService.createGame()
            .then(response => {
                console.log('response: ' + JSON.stringify(response));
                this.isGameOn = true;
                this.gameHost = JSON.parse(localStorage.getItem("currentUser")).ssoId;
                console.log('gameHost: ' + this.gameHost);
                this.playersSign = 'O';
                this.getGamesState();
                this.setUpSubscriptionTimer();
            })
            .catch((error: any) => {
                console.log('error: ' + JSON.stringify(error));
            });
    }



}