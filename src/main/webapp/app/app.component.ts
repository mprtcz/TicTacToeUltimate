import {Component} from '@angular/core';
import {MaterialDesignUpgradeElement} from "./material-design-upgrade-element";
import {EditUserService} from "./shared/edit-user.service";
import {CurrentUserService} from "./shared/current-user.service";

@Component({
    selector: 'my-app',
    templateUrl: 'app/app.component.jsp',
    providers: [EditUserService, CurrentUserService],
    directives: [MaterialDesignUpgradeElement]
})
export class AppComponent {}