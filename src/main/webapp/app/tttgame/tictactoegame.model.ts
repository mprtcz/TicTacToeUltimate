export class TicTacToeGame {
    symbols: string[] = [9];

    constructor() {
        for(let i = 0 ; i < 9; i++) {
            this.symbols[i] = ' ';
        }
    }
}