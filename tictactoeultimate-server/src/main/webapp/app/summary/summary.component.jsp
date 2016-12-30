<style>
    .mdl-card__title {
        padding: 0;
    }

    .mdl-card__supporting-text {
        padding: 0;
    }

    .mdl-data-table__cell--non-numeric {
        padding-right: 0;
        padding-left: 0;
    }

    .mdl-card {
        justify-content: center;
        margin-top: 25px;
        padding: 0;
        min-height: 0;
    }

    .autowidth {
        width: 50%;
        min-width: 200px;
        justify-content: center;
    }

    .halfwidth {
        min-width: 200px;
        width: 50%;
    }

    .mdl-data-table {
        width: 100%;
        border: 0;
        position: relative;
    }

    .autoheight {
        position: relative;
        display: inline-block !important;
    }
</style>
<section mdl-upgrade class="section--center tab-content">
    <div class="mdl-card autowidth mdl-grid  mdl-shadow--2dp">
        <div class="mdl-card__title"><h4>Summary </h4></div>
        <div class="mdl-card__supporting-text">
            <h6 *ngIf="onlineUsers"> Users Online: {{onlineUsers.length}}</h6>
            <h6 *ngIf="currentGames"> Games Online: {{currentGames.length}}</h6>
        </div>
    </div>
    <div class="mdl-grid autowidth">
        <div class="mdl-card halfwidth autoheight mdl-shadow--2dp">
            <table class="mdl-data-table mdl-js-data-table">
                <thead>
                <tr>
                    <th class="mdl-data-table__cell--non-numeric">Online Users</th>
                    <th class="mdl-data-table__cell--non-numeric"></th>
                </tr>
                </thead>
                <tbody>
                <tr *ngFor="let nickname of onlineUsers">
                    <td class="mdl-data-table__cell--non-numeric">
                        <i class="material-icons mdl-list__item-icon">person</i>
                    </td>
                    <td class="mdl-data-table__cell--non-numeric">{{nickname}}</td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="mdl-card halfwidth autoheight mdl-shadow--2dp">
            <table class="mdl-data-table mdl-js-data-table">
                <thead>
                <tr>
                    <th class="mdl-data-table__cell--non-numeric">Online Games</th>
                    <th class="mdl-data-table__cell--non-numeric"></th>
                </tr>
                </thead>
                <tbody>
                <tr *ngFor="let game of currentGames">
                    <td class="mdl-data-table__cell--non-numeric">
                        <i class="material-icons mdl-list__item-icon">gamepad</i>
                    </td>
                    <td class="mdl-data-table__cell--non-numeric">{{game.gameHost}} vs
                            <i *ngIf="!game.secondPlayer">...</i>{{game.secondPlayer}}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</section>