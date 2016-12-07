import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from "./app.component";
import {CustomLoginComponent} from "./login/custom-login.component";
import {GreetingComponent} from "./greeting/greeting.component";
import {LogoutComponent} from "./logout/logout.component";
import {UserComponent} from "./user/user.component";
import {RegisterComponent} from "./register/register.component";
import {GamesComponent} from "./games/games.component";
import {TicTacToeComponent} from "./tttgame/tic-tac-toe.component";

const routes: Routes = [
    {path: '', redirectTo: '/greeting', pathMatch: 'full'},
    {path: 'login', redirectTo: '/customlogin', pathMatch: 'full'},
    {path: 'start', component: AppComponent},
    {path: 'customlogin', component: CustomLoginComponent},
    {path: 'greeting', component: GreetingComponent},
    {path: 'logout', component: LogoutComponent},
    {path: 'profile', component: UserComponent},
    {path: 'games', component: GamesComponent},
    {path: 'tictactoe', component: TicTacToeComponent},
    {path: 'register', component: RegisterComponent}
];
@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}