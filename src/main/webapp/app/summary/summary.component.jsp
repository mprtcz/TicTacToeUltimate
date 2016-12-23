<style>
    .loginCard:last-child {
        padding:5px;
        width: 50%;
    }
    .mdl-data-table {
        margin: 15px;
    }
</style>
<section mdl-upgrade class="section--center tab-content">
    <div class="mdl-card loginCard mdl-grid mdl-grid--no-spacing mdl-shadow--2dp">
        <div class="mdl-card__title"><h4>Summary </h4></div>
        <div class="mdl-card__supporting-text">
            <h6 *ngIf="onlineUsers"> Users Online: {{onlineUsers.length}}</h6>
            <h6 *ngIf="onlineUsers"> Games Online: null</h6>
        </div>
    </div>
    <div class="mdl-card loginCard mdl-grid mdl-shadow--2dp">
        <div mdl-upgrade class="mdl-grid mdl-grid--no-spacing">
            <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
                <thead>
                <tr>
                    <th class="mdl-data-table__cell--non-numeric">Online Users:</th>
                </tr>
                </thead>
                <tbody>
                <tr *ngFor="let nickname of onlineUsers">
                    <td class="mdl-data-table__cell--non-numeric">
                        <i class="material-icons">person_pin</i> {{nickname}}
                    </td>
                </tr>
                </tbody>
            </table>
            <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
                <thead>
                <tr>
                    <th class="mdl-data-table__cell--non-numeric">Online Users:</th>
                </tr>
                </thead>
                <tbody>
                <tr *ngFor="let nickname of onlineUsers">
                    <td class="mdl-data-table__cell--non-numeric">
                        <i class="material-icons">person_pin</i> {{nickname}}
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</section>