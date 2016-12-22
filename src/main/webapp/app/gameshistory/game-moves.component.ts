import { Component, OnInit } from '@angular/core';
import {Input} from "@angular/core";

@Component({
    moduleId: module.id,
    selector: 'games-moves',
    templateUrl: '/app/gameshistory/game-moves.component.jsp'
})
export class GameMovesComponent implements OnInit {

    @Input('gamemoves') gamemoves : any[];
}