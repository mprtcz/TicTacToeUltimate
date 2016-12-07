<style>
    .my-textfield-error {
        color: #d50000;
        position: absolute;
        font-size: 12px;
        margin-top: 3px;
        visibility: visible;
        display: block
    }

    .mdl-textfield__input {
        text-align: right;
    }
</style>
<section mdl-upgrade class="section--center tab-content">
    <div class="mdl-card loginCard mdl-grid mdl-grid--no-spacing mdl-shadow--2dp">
        <h4 class="mdl-card__title-text">{{headlineText}}</h4>
        <form action="#" name="registerForm">
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label" *ngIf="!isEditing">
                <input class="mdl-textfield__input" name="username" type="text" id="username"
                       [(ngModel)]="newUser.ssoId" (click)="constraintViolationsObj.ssoid = ''">
                <label class="mdl-textfield__label" for="username">Username</label>
                <span *ngIf="constraintViolationsObj.ssoid"
                      class="my-textfield-error">{{constraintViolationsObj.ssoid}}</span>
            </div>
            <br/>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" name="nickname" type="text" id="nickname"
                       [(ngModel)]="newUser.nickname" (click)="constraintViolationsObj.nickname = ''">
                <label class="mdl-textfield__label" for="nickname">Nickname (For others to see)</label>
                <span *ngIf="constraintViolationsObj.nickname" class="my-textfield-error">{{constraintViolationsObj.nickname}}</span>
            </div>
            <br/>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" name="password" type="password" id="password"
                       [(ngModel)]="newUser.password" (click)="constraintViolationsObj.password = ''">
                <label class="mdl-textfield__label" for="password">Password</label>
                <span *ngIf="constraintViolationsObj.password" class="my-textfield-error">{{constraintViolationsObj.password}}</span>
            </div>
            <br/>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" name="passwordConf" type="password" id="passwordConf"
                       pattern="{{newUser.password}}" [(ngModel)]="passwordConf">
                <label class="mdl-textfield__label" for="passwordConf">Confirm Password</label>
                <span class="mdl-textfield__error">Passwords do not match</span>
            </div>
            <br/>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" name="email" type="email" id="email"
                       [(ngModel)]="newUser.email" (click)="constraintViolationsObj.email = ''">
                <label class="mdl-textfield__label" for="email">Email</label>
                <span *ngIf="constraintViolationsObj.email"
                      class="my-textfield-error">{{constraintViolationsObj.email}}</span>
            </div>
            <br/>
            <!-- Part responsible for granting authorities, won't run scripts while ngIf is in tag -->
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label getmdl-select getmdl-select__fullwidth"
                     *ngIf="isAdmin">
                    <input class="mdl-textfield__input" name="role" type="text" id="role"
                           tabIndex="-1" [(ngModel)]="newUser.role">
                    <label for="role" class="mdl-textfield__label">Role</label>
                    <ul class="mdl-menu mdl-menu--top-left mdl-js-menu mdl-js-ripple-effect"
                        data-mdl-for="demo-menu-top-left" for="role">
                        <li class="mdl-menu__item" (click)="newUser.role = 'ROLE_USER'">User</li>
                        <li class="mdl-menu__item" (click)="newUser.role = 'ROLE_ADMIN'">ADMIN</li>
                    </ul>
            </div>
            <br/>
            <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent"
                    (click)="validateAndSubmit()">
                SUBMIT
            </button>
        </form>
        <div *ngIf="message" class="mdl-card__actions mdl-card--border">
            <h4 class="mdl-card__title-text">{{message}}</h4>
        </div>
    </div>


</section>
