import {Component, OnInit} from '@angular/core';
import {SummaryService} from "./summary.service";

@Component({
    moduleId: module.id,
    selector: 'summary',
    templateUrl: '/app/summary/summary.component.jsp',
    providers: [SummaryService]
})
export class SummaryComponent implements OnInit {
    private onlineUsers: any[];

    constructor(private summaryService : SummaryService) {}

    ngOnInit() {
        console.log('Summary Component Initialized');
        this.getOnlineUsers();
    }

    getOnlineUsers() : void {
        this.summaryService.getOnlineUsers()
            .then(res => {
                console.log("Response: " +JSON.stringify(res));
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
}