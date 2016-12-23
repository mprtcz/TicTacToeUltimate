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

    ngOnInit() {console.log('Summary Component Initialized');}

    getOnlineUsers() : void {
        this.summaryService.getOnlineUsers()
            .then(res => {
                console.log("Response: " +JSON.stringify(res));
                this.onlineUsers ;
                console.log("Added this.user: " + JSON.stringify(this.user));
                localStorage.setItem("currentUser", JSON.stringify(this.user));
                console.log("Added user: " + JSON.stringify(localStorage.getItem("currentUser")));
                this.message = 'Success';
                this.router.navigate(['/greeting']);
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