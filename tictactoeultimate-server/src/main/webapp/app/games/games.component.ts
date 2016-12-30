import {Component, OnInit} from "@angular/core";
import {GamesService} from "./games.service";
import {Router} from "@angular/router";
import {GameDataholderService} from "../shared/game-datahoder.service";

@Component({
    moduleId: module.id,
    selector: 'games',
    templateUrl: '/app/games/games.component.jsp',
    providers: [
        GamesService
    ]
})
export class GamesComponent implements OnInit {

    ngOnInit(): void {
        this.isDisplaying = false;
    }

    private isDisplaying: boolean;

    constructor(private router: Router, private gameDataholderService: GameDataholderService) {
    }

    reroute(): void {
        this.router.navigate(['/tictactoe']);
    }

    getNotification(event): void {
        this.gameDataholderService.isCreating = false;
        this.gameDataholderService.gameHost = event.gameHost;
        this.reroute();
    }

    createNewGame(): void {
        this.gameDataholderService.isCreating = true;
        this.reroute();
    }
}