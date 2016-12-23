<style>
    .mdl-card:last-child {
        padding: 5px;
        justify-content: center;
    }

    .mdl-card__title {
        padding: 0;
    }

    .mdl-card__supporting-text {
        padding: 0;
    }

    .mdl-card {
        justify-content: center;
        margin-top: 25px;
    }

    .mdl-data-table {
        margin: 15px;
    }
</style>
<section mdl-upgrade class="section--center tab-content">
    <div class="mdl-card  mdl-grid mdl-grid--no-spacing mdl-shadow--2dp">
        <div class="mdl-card__title"><h4>Summary </h4></div>
        <div class="mdl-card__supporting-text">
            <h6 *ngIf="onlineUsers"> Users Online: {{onlineUsers.length}}</h6>
            <h6 *ngIf="onlineUsers"> Games Online: null</h6>
        </div>
    </div>
    <div class="mdl-card mdl-grid mdl-shadow--2dp">
        <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
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
                <td class="mdl-data-table__cell--non-numeric">
                    {{nickname}}
                </td>
            </tr>
            </tbody>
        </table>

        <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
            <thead>
            <tr>
                <th class="mdl-data-table__cell--non-numeric">Open Games</th>
                <th class="mdl-data-table__cell--non-numeric"></th>
            </tr>
            </thead>
            <tbody>
            <tr *ngFor="let nickname of onlineUsers">
                <td class="mdl-data-table__cell--non-numeric">
                    <i class="material-icons mdl-list__item-icon">person</i>
                </td>
                <td class="mdl-data-table__cell--non-numeric">
                    {{nickname}}
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</section>