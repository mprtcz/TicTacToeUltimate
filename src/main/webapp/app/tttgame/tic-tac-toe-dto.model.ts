export  class TicTacToeDTO {
    gameIs: number;
    table : any;
    oneDimTable : string[] = [9];
    currentPlayer : string;
    gameHost : string;
    secondPlayer: string;
    winner : string;
    horizontalSums : any;
    verticalSums: any;
    diagonalSums: any;
}