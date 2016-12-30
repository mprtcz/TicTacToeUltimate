import {Component, AfterViewInit} from '@angular/core';
import {GreetingService} from "./greeting.service";

@Component({
    moduleId: module.id,
    selector: 'fa-greeting',
    templateUrl: '/app/greeting/greeting.component.jsp',
    providers: [ GreetingService ]
})
export class GreetingComponent {
    variable: string;

    constructor(private greetingService: GreetingService) {
    }

    string: message;

    getSimpleGreeting(): void {
        this.greetingService.getSimpleGreetingFromServer()
            .then(message => this.message = message)
    }

    getPersonalizedGreeting(): void {
        this.greetingService.getPersonalizedMessageFromServer(this.variable)
            .then(message => this.message = message)
    }
}
