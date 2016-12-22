import {Component, OnInit} from '@angular/core';
import {Input} from "@angular/core";
import {GamesHistoryService} from "./games-history.service";

@Component({
    moduleId: module.id,
    selector: 'games-history',
    templateUrl: '/app/gameshistory/games-history.component.jsp',
    providers: [
        GamesHistoryService
    ]
})
export class GamesHistoryComponent implements OnInit {
    private gamesHistory : any[];
    private movesToDisplay : any[];

    @Input('username') username : String;

    constructor(private gamesHistoryService : GamesHistoryService) {
    }

    ngOnInit() {
        this.gamesHistoryService.getDataFromServer(this.username)
            .then(resp => {
                console.log(JSON.stringify(resp));
                this.gamesHistory = JSON.parse(resp._body);
                console.log('firstGame id : ' + JSON.stringify(this.gamesHistory[0].id));
                console.log('first game playerOne: ' + JSON.stringify(this.gamesHistory[0].playerOne.nickname));
            }).catch(function (error) {
            console.log("Error " + JSON.stringify(error));
        });
    }

    setMoves(movesToDisplay: any) : void {
        console.log(JSON.stringify(movesToDisplay));
        this.movesToDisplay = movesToDisplay;
    }
}