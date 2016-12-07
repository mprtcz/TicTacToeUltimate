<style>
    .mdl-card {
        width: inherit;
        justify-items: center;
    }
</style>
<section mdl-upgrade class="section--center mdl-grid tab-content">
    <div class="mdl-card loginCard mdl-grid mdl-shadow--2dp">
        <div class="mdl-card mdl-cell mdl-cell--12-col">
            <div class="mdl-card__title mdl-card--expand">
                <h2 class="mdl-card__title-text">Start a Game</h2>
            </div>
            <ul class="mdl-list">
                <li class="mdl-list__item">
                    <span class="mdl-list__item-primary-content">Start a Tic Tac Toe Game</span>
                    <span class="mdl-list__item-secondary-content">
                    <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent"
                            routerLink="/tictactoe">Start</button>
                    </span>
                </li>
                <li class="mdl-list__item">
                    <span class="mdl-list__item-primary-content">Start Some Other Game</span>
                    <span class="mdl-list__item-secondary-content">
                    <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent"
                            routerLink="/">Start</button>
                    </span>
                </li>
            </ul>
        </div>
    </div>
</section>