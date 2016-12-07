<style>
    .mdl-button--colored {
        color: white;
        text-transform: none;
    }
</style>
<div mdl-upgrade class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <header class="mdl-layout__header">
        <div class="mdl-layout__header-row">
            <!-- Title -->
            <span class="mdl-layout-title">The Service</span>
            <!-- Add spacer, to align navigation to the right -->
            <div class="mdl-layout-spacer"></div>
            <!-- Navigation. We hide it in small screens. -->
            <nav class="mdl-navigation">
                <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--colored"
                   *ngIf="!user" routerLink="/login">
                    <i class="material-icons md-light" mdl-upgrade="true">touch_app</i> Login</a>
                <a  class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--colored"
                    *ngIf="!user" routerLink="/register">
                    <i class="material-icons md-light">library_add</i> Register</a>
                <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--colored"
                   *ngIf="user" routerLink="/profile">
                    <i class="material-icons md-light" mdl-upgrade>account_box</i> {{user.nickname}}</a>
                <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--colored"
                   *ngIf="user" routerLink="/logout">
                    <i class="material-icons md-light">exit_to_app</i> Logout</a>
            </nav>
        </div>
        <div class="mdl-layout__tab-bar mdl-js-ripple-effect">
            <a routerLink="/greeting" class="mdl-layout__tab">Greeting</a>
            <a routerLink="/games" class="mdl-layout__tab"><div *ngIf="user">Start a Game</div></a>
        </div>
    </header>
    <main class="mdl-layout__content">
        <section class="mdl-layout__tab-panel is-active">
            <div class="page-content"><router-outlet></router-outlet></div>
        </section>
    </main>
</div>