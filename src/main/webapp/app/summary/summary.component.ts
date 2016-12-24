import {Component, OnInit} from '@angular/core';
import {SummaryService} from "./summary.service";
import {GamesService} from "../games/games.service";

@Component({
    moduleId: module.id,
    selector: 'summary',
    templateUrl: '/app/summary/summary.component.jsp',
    providers: [
        SummaryService,
        GamesService
    ]
})
export class SummaryComponent implements OnInit {
    private onlineUsers: any[];
    private currentGames: any[];

    constructor(private summaryService: SummaryService, private gamesService: GamesService) {
    }

    ngOnInit() {
        console.log('Summary Component Initialized');
        this.getOnlineUsers();
        this.getCurrentGames();
        //this.setDummyData();
    }

    getOnlineUsers(): void {
        this.summaryService.getOnlineUsers()
            .then(res => {
                console.log("Response: " + JSON.stringify(res));
                this.onlineUsers = JSON.parse(res._body);
                console.log("Added onlineUsers: " + JSON.stringify(this.onlineUsers));
            })
            .catch((error: any) => {
                if (error.status === 401) {
                    console.log("Error 401")
                } else {
                    console.log("Error " + error.status);
                }
                console.log('error occurred!');
                this.message = 'Bad Credentials';
            });
    }

    getCurrentGames(): void {
        this.gamesService.fetchTTTGamesFromServer()
            .then(res => {
                console.log("Response: " + JSON.stringify(res));
                this.currentGames = JSON.parse(res._body);
                console.log("Added onlineUsers: " + JSON.stringify(this.onlineUsers));
            })
            .catch((error: any) => {
                if (error.status === 401) {
                    console.log("Error 401")
                } else {
                    console.log("Error " + error.status);
                }
                console.log('error occurred!');
                this.message = 'Bad Credentials';
            });
    }

    setDummyData() : void {
        this.onlineUsers  = new Array(6);
        this.onlineUsers[0] = "33";
        this.onlineUsers[1]=  "32";
        this.onlineUsers[2]=  "33";
        this.onlineUsers[3]=  "34";
        this.onlineUsers[4]=  "35";
        this.onlineUsers[5]=  "36";

        this.currentGames = new Array(9);
        this.currentGames[0] = new Dummy("first", "second");
        this.currentGames[1] = new Dummy("first", "second");
        this.currentGames[2] = new Dummy("first", "second");
        this.currentGames[3] = new Dummy("first", "second");
        this.currentGames[4] = new Dummy("first", "second");
        this.currentGames[5] = new Dummy("first", "second");
        this.currentGames[6] = new Dummy("first", "second");
        this.currentGames[7] = new Dummy("first", "second");
        this.currentGames[8] = new Dummy("first", "second");
    }

}

class Dummy {
    gameHost : string;
    secondPlayer : string;


    constructor(gamehost: string, secondPlayer: string) {
        this.gameHost = gamehost;
        this.secondPlayer = secondPlayer;
    }
}