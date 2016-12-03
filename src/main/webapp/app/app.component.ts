import {Component, OnInit} from '@angular/core';
import {MaterialDesignUpgradeElement} from "./material-design-upgrade-element";

@Component({
    selector: 'my-app',
    templateUrl: 'app/app.component.jsp',
    directives: [MaterialDesignUpgradeElement]
})
export class AppComponent implements OnInit {}