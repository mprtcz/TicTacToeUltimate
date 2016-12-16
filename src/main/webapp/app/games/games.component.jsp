<style>
    .mdl-card {
        width: inherit;
        margin-top: 20px;
        margin-left: 10px;
        justify-items: center;
    }
</style>
<section mdl-upgrade class="section--center mdl-grid tab-content">
    <div class=" mdl-grid mdl-grid--no-spacing">
        <div class="mdl-card loginCard mdl-grid  mdl-shadow--2dp">
            <div class="mdl-card mdl-cell mdl-cell--12-col">
                <div class="mdl-card__title mdl-card--expand">
                    <h2 class="mdl-card__title-text">Start a Game</h2>
                </div>
                <ul class="mdl-list">
                    <li class="mdl-list__item">
                        <span class="mdl-list__item-primary-content">Tic Tac Toe Game</span>
                        <span class="mdl-list__item-secondary-content">
                    <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent"
                            (click)="createNewGame()">Start</button>
                    </span>
                        <span class="mdl-list__item-secondary-content">
                    <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent"
                            (click)="isDisplaying = true">Join</button>
                    </span>
                </ul>
            </div>
        </div>
        <div class="mdl-card  mdl-grid mdl-shadow--2dp" *ngIf="isDisplaying">
            <games-list (notifyParent)="getNotification($event)"></games-list>
        </div>
    </div>
</section>