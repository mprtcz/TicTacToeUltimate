import {Component, OnInit} from "@angular/core";
import {TicTacToeGame} from "./tictactoegame.model";

@Component({
    moduleId: module.id,
    selector: 'tttgame',
    templateUrl: '/app/tttgame/tic-tac-toe.component.jsp'
})
export class TicTacToeComponent implements OnInit {
    private game: TicTacToeGame;
    private currentSymbol: string = 'X';

    ngOnInit(): void {
        this.game = new TicTacToeGame();
    }

    private isGameOn: boolean;

    insertSymbol(index: int): void {
        console.log('insert symbol: '+index);
        if (this.game.symbols[index] == ' ') {
            this.game.symbols[index] = this.currentSymbol;
            this.reverseSymbol();
        }
    }

    clear() : void {
        this.game = new TicTacToeGame();
    }

    reverseSymbol() : void {
        if(this.currentSymbol == 'X') {
            this.currentSymbol = 'O';
        } else {
            this.currentSymbol = 'X';
        }
    }

}