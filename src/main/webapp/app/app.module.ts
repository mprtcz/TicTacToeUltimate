import {NgModule}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from "./app.component";
import {GreetingComponent} from "./greeting/greeting.component";
import {GreetingService} from "./greeting/greeting.service";
import {HttpModule} from "@angular/http";

@NgModule({
    imports: [
        BrowserModule,
        HttpModule
    ],
    declarations: [
        AppComponent,
        GreetingComponent
    ],
    providers: [GreetingService],
    bootstrap: [AppComponent]
})
export class AppModule {
}
