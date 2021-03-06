<style>
    .mdl-data-table {
        margin-left: 14px;
    }
</style>
<div mdl-upgrade class="mdl-grid mdl-grid--no-spacing">
    <div *ngIf="gamemoves">
        <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
            <thead>
            <tr>
                <th>Field</th>
                <th>Symbol</th>
                <th>Time</th>
            </tr>
            </thead>
            <tbody>
            <tr *ngFor="let move of gamemoves">
                <td class="mdl-data-table__cell--non-numeric">{{move.field}}</td>
                <td class="mdl-data-table__cell--non-numeric">{{move.symbol}}</td>
                <td class="mdl-data-table__cell--non-numeric">
                    {{move.dateTime.hour}}:{{move.dateTime.minute}}:{{move.dateTime.second}}
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>