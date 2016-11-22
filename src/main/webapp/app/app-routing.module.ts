import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from "./app.component";
import {CustomLoginComponent} from "./login/custom-login.component";
import {GreetingComponent} from "./greeting/greeting.component";
import {LogoutComponent} from "./logout/logout.component";
import {UserComponent} from "./user/user.component";

const routes: Routes = [
    {path: '', redirectTo: '/greeting', pathMatch: 'full'},
    {path: 'login', redirectTo: '/customlogin', pathMatch: 'full'},
    {path: 'start', component: AppComponent},
    {path: 'customlogin', component: CustomLoginComponent},
    {path: 'greeting', component: GreetingComponent},
    {path: 'logout', component: LogoutComponent},
    {path: 'principal', component: UserComponent}
];
@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}