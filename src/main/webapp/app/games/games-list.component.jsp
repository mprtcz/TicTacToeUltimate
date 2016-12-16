<table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" mdl-upgrade>
    <thead>
    <tr>
        <th class="mdl-data-table__cell--non-numeric">Game Host</th>
        <th class="mdl-data-table__cell--non-numeric">Second Player</th>
    </tr>
    </thead>
    <tbody *ngFor="let game of tttGames">
    <tr>
        <td class="mdl-data-table__cell--non-numeric">{{game.gameHost}}</td>
        <td class="mdl-data-table__cell">
            <div *ngIf="game.secondPlayer">{{game.secondPlayer}}</div>
            <div *ngIf="!game.secondPlayer">
                <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent"
                        (click)="joinGame(game)" mdl-upgrade>Join
                </button>
            </div>
        </td>
    </tr>
    </tbody>
</table>