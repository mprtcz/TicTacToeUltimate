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

    .updownborders {
        border-bottom: groove !important;
        border-top: groove !important;
    }

    .mdl-card {
        min-width: 350px;
        justify-content: center;
    }

    .sideborders {
        border-right: groove !important;
        border-left: groove !important;
    }

    .center {
        justify-content: center;
        background-color: red;
    }
</style>
<section mdl-upgrade class="section--center mdl-grid tab-content">
    <div class="mdl-card loginCard mdl-grid mdl-shadow--2dp">
        <div class="mdl-card mdl-cell mdl-cell--12-col" mdl-upgrade>
            <div><h4>Waiting for opponent's move</h4></div>
            <div class="demo-grid-ruler mdl-grid">
                <div class="mdl-cell mdl-cell--1-col" (click)="insertSymbol(0)"> </div>
                <div class="mdl-cell sideborders mdl-cell--1-col" (click)="insertSymbol(1)"> </div>
                <div class="mdl-cell mdl-cell--1-col" (click)="insertSymbol(2)"> </div>
            </div>
            <div class="demo-grid-ruler updownborders mdl-grid">
                <div class="mdl-cell mdl-cell--1-col" (click)="insertSymbol(3)"> </div>
                <div class="mdl-cell sideborders mdl-cell--1-col" (click)="insertSymbol(4)"></div>
                <div class="mdl-cell  mdl-cell--1-col" (click)="insertSymbol(5)"></div>
            </div>
            <div class="demo-grid-ruler mdl-grid">
                <div class="mdl-cell mdl-cell--1-col" (click)="insertSymbol(6)"></div>
                <div class="mdl-cell sideborders mdl-cell--1-col" (click)="insertSymbol(7)"></div>
                <div class="mdl-cell mdl-cell--1-col" (click)="insertSymbol(8)"></div>
            </div>
            <ul class="demo-list-item mdl-list">
                <li class="mdl-list__item"><span class="mdl-list__item-primary-content">Game host: dummyName</span></li>
                <li class="mdl-list__item"><span class="mdl-list__item-primary-content">Second player: DUMMY NAME</span></li>
            </ul>
            <h4 >Creating game...</h4>
            <div class="mdl-grid">
            <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent"
                    >Leave the Game</button></div>
        </div>
    </div>
</section>