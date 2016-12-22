import {Component} from '@angular/core';
import {MaterialDesignUpgradeElement} from "./material-design-upgrade-element";
import {EditUserService} from "./shared/edit-user.service";
import {CurrentUserService} from "./shared/current-user.service";
import {GamesListComponent} from "./games/games-list.component";
import {GameDataholderService} from "./shared/game-datahoder.service";
import {GamesHistoryComponent} from "./gameshistory/games-history.component";

@Component({
    selector: 'my-app',
    templateUrl: 'app/app.component.jsp',
    providers: [EditUserService, CurrentUserService, GameDataholderService],
    directives: [MaterialDesignUpgradeElement, GamesListComponent, GamesHistoryComponent]
})
export class AppComponent {}