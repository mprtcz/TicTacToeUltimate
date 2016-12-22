<style>
    .mdl-grid:first-child {
        margin-left: 25%;
    }

    .mdl-grid {
        justify-content: center;
    }

    .mdl-list {
        background-color: white;
    }

    .mdl-button {
        margin: 14px;
    }
</style>
<section mdl-upgrade class="section--center tab-content">
    <div class="mdl-grid mdl-grid--no-spacing" *ngIf="user">
        <ul class="mdl-list mdl-shadow--2dp">
            <li class="mdl-list__item">
                <ul class="demo-list-icon mdl-list">
                    <li class="mdl-list__item"><span class="mdl-list__item-primary-content">
                        <i class="material-icons mdl-list__item-icon">person</i>User ID:</span>
                        <span class="mdl-list__item-secondary-content"><b>{{user.ssoId}}</b></span>
                    </li>
                    <li class="mdl-list__item"><span class="mdl-list__item-primary-content">
                        <i class="material-icons mdl-list__item-icon">person_pin</i>User Nickname:</span>
                        <span class="mdl-list__item-secondary-content"><b>{{user.nickname}}</b></span>
                    </li>
                    <li class="mdl-list__item"><span class="mdl-list__item-primary-content">
                        <i class="material-icons mdl-list__item-icon">contact_mail</i>User Email:</span>
                        <span class="mdl-list__item-secondary-content"><b>{{user.email}}</b></span>
                    </li>
                </ul>
            </li>
        </ul>
        <div class="mdl-cell">
            <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent"
                    (click)="deleteAccount()">
                Delete Account
            </button>
            <br/>
            <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored"
                    (click)="setUser()" routerLink="/register">
                Edit Info
            </button>
            <br/>
            <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored"
                    (click)="isDisplaying = !isDisplaying">
                Show history
            </button>
        </div>
    </div>
    <div class="mdl-grid" *ngIf="isDisplaying">
        <games-history [username]="user.ssoId"></games-history>
    </div>
</section>