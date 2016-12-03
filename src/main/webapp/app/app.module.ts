import {NgModule}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule}   from '@angular/forms';

import {AppComponent} from "./app.component";
import {GreetingComponent} from "./greeting/greeting.component";
import {HttpModule} from "@angular/http";
import {AppRoutingModule} from "./app-routing.module";
import {CustomLoginComponent} from "./login/custom-login.component";
import {NavbarComponent} from "./navbar/navbar.component";
import {LogoutComponent} from "./logout/logout.component";
import {UserComponent} from "./user/user.component";
import {RegisterComponent} from "./register/register.component";
import {MaterialDesignUpgradeElement} from "./material-design-upgrade-element";

@NgModule({
    imports: [
        BrowserModule,
        HttpModule,
        FormsModule,
        AppRoutingModule
    ],
    declarations: [
        AppComponent,
        GreetingComponent,
        CustomLoginComponent,
        NavbarComponent,
        LogoutComponent,
        UserComponent,
        RegisterComponent,
        MaterialDesignUpgradeElement
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
