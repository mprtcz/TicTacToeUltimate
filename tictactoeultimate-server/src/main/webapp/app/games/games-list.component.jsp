<style>
    .mdl-data-table {
        width: 100%;
        border: 0;
    }
    .mdl-data-table__cell {
        text-align: center;
    }
</style>
<table class="mdl-data-table mdl-js-data-table" mdl-upgrade>
    <thead>
    <tr>
        <th class="mdl-data-table__cell">Game Host</th>
        <th class="mdl-data-table__cell">Second Player</th>
    </tr>
    </thead>
    <tbody *ngFor="let game of tttGames">
    <tr>
        <td class="mdl-data-table__cell">{{game.gameHost}}</td>
        <td class="mdl-data-table__cell">
            <div *ngIf="game.secondPlayer">{{game.secondPlayer}}</div>
            <div class="center" *ngIf="!game.secondPlayer">
                <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent"
                        (click)="joinGame(game)" mdl-upgrade>Join
                </button>
            </div>
        </td>
    </tr>
    </tbody>
</table>