import {Component, OnInit} from '@angular/core';
import {GreetingService} from "./greeting.service";

@Component({
    moduleId: module.id,
    selector: 'fa-greeting',
    templateUrl: './greeting.component.jsp'
})
export class GreetingComponent implements OnInit {
    constructor(private greetingService: GreetingService) {
        console.log("Service: " +this.greetingService.toString());
    }

    ngOnInit(): void {
        console.log("init greeting component");
    }

    string: message;

    getSimpleGreeting(): void {
        console.log("simpleGreeting");
        console.log("service: " +this.greetingService.toString());
        //this.message = this.greetingService.getMockMessage();
        this.greetingService.getSimpleGreetingFromServer().then(message => this.message = message)
    }
}
