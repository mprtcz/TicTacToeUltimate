<section mdl-upgrade class="section--center tab-content">
    <div class="mdl-card loginCard mdl-grid mdl-grid--no-spacing mdl-shadow--2dp">
        <h4 class="mdl-card__title-text">Log In</h4>
        <form action="#">
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" name="username" type="text" id="username"
                       [(ngModel)]="username">
                <label class="mdl-textfield__label" for="username">Username</label>
            </div>
            <br/>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" name="password" type="password" id="password"
                       [(ngModel)]="password">
                <label class="mdl-textfield__label" for="password">Password</label>
            </div>
            <br/>
            <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent"
                    (click)="getData()">
                SUBMIT
            </button>
        </form>
        <div *ngIf="message" class="mdl-card__actions mdl-card--border">
            <h4 class="mdl-card__title-text">{{message}}</h4>
        </div>
    </div>
</section>

