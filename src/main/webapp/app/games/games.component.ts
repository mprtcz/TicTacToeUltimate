import {Component, OnInit} from "@angular/core";
import {GamesService} from "./games.service";
import {Router} from "@angular/router";
import {GameDataholderService} from "../shared/game-datahoder.service";

@Component({
    moduleId: module.id,
    selector: 'games',
    templateUrl: '/app/games/games.component.jsp',
    providers: [
        GamesService,
        GameDataholderService
    ]
})
export class GamesComponent implements OnInit {

    ngOnInit(): void {
        this.isDisplaying = false;
    }

    constructor(private router : Router, private gameDataholderService: GameDataholderService) {
    }

    private isDisplaying: boolean;

    reroute() : void {
        this.router.navigate(['/tictactoe']);
    }

    getNotification(event) : void {
        if(event) {
            this.gameDataholderService.isCreating = false;
            this.reroute();
        }
    }

    createNewGame() : void {
        this.gameDataholderService.isCreating = true;
        this.reroute();
    }


}