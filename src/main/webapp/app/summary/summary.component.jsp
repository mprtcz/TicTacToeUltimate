<section mdl-upgrade class="section--center tab-content">
    <div class="mdl-card loginCard mdl-grid mdl-grid--no-spacing mdl-shadow--2dp">
        <div class="mdl-card__title"> <h4>Summary</h4></div>
        <div mdl-upgrade class="mdl-grid mdl-grid--no-spacing">
            <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
                <thead>
                <tr>
                    <th class="mdl-data-table__cell--non-numeric">Online Users:</th>
                </tr>
                </thead>
                <tbody>
                <tr *ngFor="let nickname of onlineUsers">
                    <td class="mdl-data-table__cell--non-numeric">{{nickname}}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</section>