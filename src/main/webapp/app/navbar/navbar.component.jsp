<div mdl-upgrade class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <header class="mdl-layout__header">
        <div class="mdl-layout__header-row">
            <!-- Title -->
            <span class="mdl-layout-title">The Service</span>
            <!-- Add spacer, to align navigation to the right -->
            <div class="mdl-layout-spacer"></div>
            <!-- Navigation. We hide it in small screens. -->
            <nav class="mdl-navigation mdl-layout--large-screen-only">
                <a *ngIf="getUser() == 'Guest'" class="mdl-navigation__link" routerLink="/login">
                    <i class="material-icons md-light">touch_app</i> Login</a>
                <a *ngIf="getUser() == 'Guest'" class="mdl-navigation__link" routerLink="/register">
                    <i class="material-icons md-light">library_add</i> Register</a>
                <a *ngIf="getUser() != 'Guest'" class="mdl-navigation__link" routerLink="/profile">
                    <i class="material-icons md-light">account_box</i> {{getUser()}}</a>
                <a *ngIf="getUser() != 'Guest'" class="mdl-navigation__link" routerLink="/logout">
                    <i class="material-icons md-light">exit_to_app</i> Logout</a>
            </nav>
        </div>
        <div class="mdl-layout__tab-bar mdl-js-ripple-effect">
            <a routerLink="/greeting" class="mdl-layout__tab">Greeting</a>
            <a routerLink="/login" class="mdl-layout__tab">Login</a>
        </div>
    </header>
    <main class="mdl-layout__content">
        <section class="mdl-layout__tab-panel is-active">
            <div class="page-content"><router-outlet></router-outlet></div>
        </section>
    </main>
</div>