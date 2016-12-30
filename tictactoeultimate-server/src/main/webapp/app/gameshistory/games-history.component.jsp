<style>
    .mdl-grid {
        padding:0;
        justify-content: center;
    }
    .mdl-cell {
        width : 255px;
    }
    .mdl-data-table {
        margin-bottom: 15px;
    }
</style>
<div mdl-upgrade class="mdl-grid mdl-grid--no-spacing">
    <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
        <thead>
        <tr>
            <th class="mdl-data-table__cell--non-numeric">First Player</th>
            <th>Second Player</th>
            <th>Date</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <tr *ngFor="let game of gamesHistory">
            <td class="mdl-data-table__cell--non-numeric">{{game.playerOne.nickname}}</td>
            <td class="mdl-data-table__cell--non-numeric">{{game.playerTwo.nickname}}</td>
            <td class="mdl-data-table__cell--non-numeric">
                {{game.dateTime.dayOfMonth}}.{{game.dateTime.monthValue}}.{{game.dateTime.year}}
            </td>
            <td class="mdl-data-table__cell--non-numeric">
                <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored"
                        (click)="setMoves(game.gameMovesList)" mdl-upgrade>Show Moves
                </button>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="mdl-cell">
    <div  *ngIf="movesToDisplay">
        <games-moves [gamemoves]="movesToDisplay"></games-moves>
    </div>
    </div>
</div>