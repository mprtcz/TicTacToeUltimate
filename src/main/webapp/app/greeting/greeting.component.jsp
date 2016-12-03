<section mdl-upgrade class="section--center mdl-grid tab-content">
    <div class="mdl-card loginCard mdl-grid mdl-shadow--2dp">
        <div class="mdl-card mdl-cell mdl-cell--12-col">
            <form action="#">
                <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent"
                        (click)="getSimpleGreeting()">
                    Get simple greeting
                </button>
                <div class="mdl-textfield mdl-js-textfield">
                    <input class="mdl-textfield__input" name="username" type="text" id="username"
                           [(ngModel)]="variable">
                    <label class="mdl-textfield__label" for="username">Username</label>
                </div>
                <br/>
                <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent"
                        (click)="getPersonalizedGreeting()">
                    Get Variable Greeting
                </button>
            </form>
            <div *ngIf="message" class="mdl-card__actions mdl-card--border">
                <h4 class="mdl-card__title-text">{{message}}</h4>
            </div>
        </div>
    </div>
</section>