<style>
    .demo-grid-ruler > .mdl-cell {
        justify-content: center;
        padding: 30px 40px 40px 30px;
        margin: 0;
        width: 50px;
        height: 50px;
        border: medium;
    }

    .demo-grid-ruler {
        padding: 0 !important;
        justify-content: center;
    }

    .mdl-card {
        min-width: 350px;
    }

    .updownborders {
        /*border-bottom-width: 1px;*/
        border-bottom: groove !important;
        border-top: groove !important;
    }

    .sideborders {
        border-right: groove !important;
        border-left: groove !important;
    }
</style>
<section mdl-upgrade class="section--center mdl-grid tab-content">
    <div class="mdl-card loginCard mdl-grid mdl-shadow--2dp">
        <div class="mdl-card mdl-cell mdl-cell--12-col" *ngIf="isGameOn" mdl-upgrade>
            <div><h4>{{gameMessage}}</h4></div>
            <div class="demo-grid-ruler mdl-grid">
                <div class="mdl-cell mdl-cell--1-col" (click)="insertSymbol(0)">{{game.symbols[0]}}</div>
                <div class="mdl-cell sideborders mdl-cell--1-col" (click)="insertSymbol(1)">{{game.symbols[1]}}</div>
                <div class="mdl-cell mdl-cell--1-col" (click)="insertSymbol(2)">{{game.symbols[2]}}</div>
            </div>
            <div class="demo-grid-ruler updownborders mdl-grid">
                <div class="mdl-cell mdl-cell--1-col" (click)="insertSymbol(3)">{{game.symbols[3]}}</div>
                <div class="mdl-cell sideborders mdl-cell--1-col" (click)="insertSymbol(4)">{{game.symbols[4]}}</div>
                <div class="mdl-cell  mdl-cell--1-col" (click)="insertSymbol(5)">{{game.symbols[5]}}</div>
            </div>
            <div class="demo-grid-ruler mdl-grid">
                <div class="mdl-cell mdl-cell--1-col" (click)="insertSymbol(6)">{{game.symbols[6]}}</div>
                <div class="mdl-cell sideborders mdl-cell--1-col" (click)="insertSymbol(7)">{{game.symbols[7]}}</div>
                <div class="mdl-cell mdl-cell--1-col" (click)="insertSymbol(8)">{{game.symbols[8]}}</div>
            </div>
            <ul class="demo-list-item mdl-list">
                <li class="mdl-list__item"><span
                        class="mdl-list__item-primary-content">Game host: {{gameDto.gameHost}}</span></li>
                <li class="mdl-list__item"><span class="mdl-list__item-primary-content">Second player: {{gameDto.secondPlayer}}</span>
                </li>
            </ul>
            <h4 *ngIf="!isGameOn">Creating game...</h4>
            <div class="mdl-grid">
                <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent"
                        (click)="leaveGame()">Leave the Game</button>
            </div>
        </div>
    </div>
</section>