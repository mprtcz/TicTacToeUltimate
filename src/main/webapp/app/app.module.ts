import {NgModule}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from "./app.component";
import {GreetingComponent} from "./greeting/greeting.component";

@NgModule({
    imports: [BrowserModule],
    declarations: [
        AppComponent,
        GreetingComponent
    ],
    bootstrap: [ AppComponent ]
})
export class AppModule {
}
