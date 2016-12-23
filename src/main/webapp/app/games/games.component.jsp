<style>
    .mdl-card {
        width: 350px;
        margin-top: 20px;
        justify-items: center;
        margin-left: 0;
    }
    .mdl-card__title {
        padding-top: 0;
        padding-bottom: 0;
        justify-content: center;
    }
    .mdl-grid {
        justify-content: center;
    }
</style>
<section mdl-upgrade class="section--center tab-content">
    <div class="mdl-grid mdl-grid--no-spacing">
        <div class="mdl-card mdl-shadow--2dp">
            <div class="mdl-card mdl-cell ">
                <div class="mdl-card__title mdl-card--expand">
                    <h4>Start a Game</h4>
                </div>
                <ul class="mdl-list">
                    <li class="mdl-list__item">
                        <span class="mdl-list__item-primary-content">Tic Tac Toe Game</span>
                        <span class="mdl-list__item-secondary-content">
                    <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored"
                            (click)="createNewGame()">Start</button>
                    </span>
                        <span class="mdl-list__item-secondary-content">
                    <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored"
                            (click)="isDisplaying = !isDisplaying">Join</button>
                    </span>
                </ul>
            </div>
        </div>
    </div>
    <div class=" mdl-grid" *ngIf="isDisplaying">
        <games-list (notifyParent)="getNotification($event)"></games-list>
    </div>
</section>