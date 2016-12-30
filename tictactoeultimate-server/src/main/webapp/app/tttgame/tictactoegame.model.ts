export class TicTacToeGameArray {
    symbols: string[] = [];

    constructor() {
        for(let i = 0 ; i < 9; i++) {
            this.symbols[i] = ' ';
        }
    }
}