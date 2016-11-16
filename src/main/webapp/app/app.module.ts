import {NgModule}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule}   from '@angular/forms';

import {AppComponent} from "./app.component";
import {GreetingComponent} from "./greeting/greeting.component";
import {GreetingService} from "./greeting/greeting.service";
import {HttpModule} from "@angular/http";
import {AppRoutingModule} from "./app-routing.module";
import {CustomLoginComponent} from "./login/custom-login.component";
import {CredentialsService} from "./credentials/credentials.service";

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
        CustomLoginComponent
    ],
    providers: [
        GreetingService,
        CredentialsService],
    bootstrap: [AppComponent]
})
export class AppModule {
}
