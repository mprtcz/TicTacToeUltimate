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
        this.terminateGame();
    }

    private game: TicTacToeGameArray;
    private gameHost: string;
    private loggedPlayer: string;
    private subscription: any;
    private isSecondPlayerInGame: boolean;
    private gameDto: TicTacToeDTO;
    private playersSign: string;
    private isGameDisabled = false;

    private gameMessage = '';

    ngOnInit(): void {
        this.game = new TicTacToeGameArray();
        this.loggedPlayer = JSON.parse(localStorage.getItem("currentUser")).ssoId;

        if (this.gameDataholderService.isCreating) {
            this.createGame()
        } else {
            this.playersSign = 'X';
            this.gameHost = this.gameDataholderService.gameHost;
            this.getGamesState();
            this.setUpSubscriptionTimer();
        }

    }

    setUpSubscriptionTimer(): void {
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

    checkIfSomeoneJoinedGame(): void {
        this.tttService.gameState(this.gameHost)
            .then(response => {
                this.updateGameDto(response);
            })
            .catch((error: any) => {
                if (error.status === 500) {
                    console.log('Server Error!');
                }
            });
    }

    constructor(private tttService: TicTacToeService,
                private router: Router,
                private gameDataholderService: GameDataholderService) {
        this.isGameOn = false;
    }

    private isGameOn: boolean;

    insertSymbol(index: int): void {
        if (this.game.symbols[index] == ' ' && !this.isGameDisabled) {
            this.game.symbols[index] = this.playersSign;
            this.tttService.parseAndSendInsertion(index)
                .then(response => {
                    this.updateGameDto(response);
                })
                .catch((error: any) => {
                    console.log('error: ' + JSON.stringify(error));
                });
        }
    }

    clear(): void {
        this.router.navigate(['/greeting']);
    }

    getGamesState(): void {
        this.tttService.gameState(this.gameHost)
            .then(response => {
                this.updateGameDto(response);
                this.isGameOn = true;
            })
            .catch((error: any) => {
                console.log('error: ' + JSON.stringify(error));
            });
    }

    determineIfThePlayerHasMove(): boolean {
        if (this.loggedPlayer == this.gameDto.gameHost) {
            return this.gameDto.currentPlayer == 'O';
        } else {
            return this.gameDto.currentPlayer == 'X';
        }
    }

    updateGameDto(response: any): void {
        this.gameDto = JSON.parse(response._body);
        this.isSecondPlayerInGame = this.gameDto.secondPlayer != null;
        this.game.symbols = this.gameDto.oneDimTable;
        this.resolveCurrentMoveMessage();
        if (this.gameDto.winner != null) {
            this.terminateGame();
        }
    }

    createGame(): void {
        this.tttService.createGame()
            .then(response => {
                this.gameHost = JSON.parse(localStorage.getItem("currentUser")).ssoId;
                this.playersSign = 'O';
                this.getGamesState();
                this.setUpSubscriptionTimer();
            })
            .catch((error: any) => {
                console.log('create game error: ' + JSON.stringify(error));
            });
    }

    resolveCurrentMoveMessage(): void {
        if(this.gameDto.secondPlayer == null) {
            this.isGameDisabled = true;
            this.gameMessage = 'Waiting for second player ';
            return;
        }
        if (this.gameDto.winner != null) {
            this.isGameDisabled = true;
            this.gameMessage = 'The winner is: ' + this.gameDto.winner;
            return;
        }
        if (this.playersSign == this.gameDto.currentPlayer) {
            this.gameMessage = 'Your move, place ' + this.playersSign;
            this.isGameDisabled = false;
        } else {
            this.isGameDisabled = true;
            this.gameMessage = 'Waiting for opponent\'s move';
        }
    }

    terminateGame(): void {
        clearInterval(this.timer);
        this.subscription.unsubscribe();
    }

    leaveGame() : void {
        this.terminateGame();
        this.router.navigate(['/summary']);
    }
}