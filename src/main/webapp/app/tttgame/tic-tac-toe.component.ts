import {Component, OnInit} from "@angular/core";
import {TicTacToeGame} from "./tictactoegame.model";
import {TicTacToeService} from "./tic-tac-toe-service";

@Component({
    moduleId: module.id,
    selector: 'tttgame',
    templateUrl: '/app/tttgame/tic-tac-toe.component.jsp',
    providers: [TicTacToeService]
})
export class TicTacToeComponent implements OnInit {
    private game: TicTacToeGame;
    private currentSymbol: string = 'X';

    ngOnInit(): void {
        console.log('ngOnInit ttt');
        this.game = new TicTacToeGame();
        this.tttService.createGame()
            .then(response => {
                console.log('response: ' + JSON.stringify(response));
                this.isGameOn = true
            })
            .catch((error: any) => {
                console.log('error: ' + JSON.stringify(error));
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
    }

    reverseSymbol(): void {
        if (this.currentSymbol == 'X') {
            this.currentSymbol = 'O';
        } else {
            this.currentSymbol = 'X';
        }
    }

}