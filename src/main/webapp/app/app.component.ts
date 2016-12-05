import {Component, OnInit} from '@angular/core';
import {MaterialDesignUpgradeElement} from "./material-design-upgrade-element";
import {EditUserService} from "./shared/edit-user.service";

@Component({
    selector: 'my-app',
    templateUrl: 'app/app.component.jsp',
    providers: [EditUserService],
    directives: [MaterialDesignUpgradeElement]
})
export class AppComponent implements OnInit {}