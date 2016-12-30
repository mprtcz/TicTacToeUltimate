import {Component, OnInit, OnChanges, SimpleChanges} from '@angular/core';
import {Input} from "@angular/core";

@Component({
    moduleId: module.id,
    selector: 'games-moves',
    templateUrl: '/app/gameshistory/game-moves.component.jsp'
})
export class GameMovesComponent implements OnChanges {

    ngOnChanges(changes: SimpleChanges): void {
        this.addZeroIfOneDigit();
    }

    @Input('gamemoves') gamemoves : any[];

    addZeroIfOneDigit() : void {
        if(this.gamemoves == null) {
            console.log('null');
            return;
        }
        for(let move of this.gamemoves) {
            console.log('move' + JSON.stringify(move));
            if(move.dateTime.minute.toString().length < 2) {
                move.dateTime.minute = '0' + move.dateTime.minute;
            }
            if(move.dateTime.second.toString().length < 2) {
                move.dateTime.second = '0' + move.dateTime.second;
            }
        }
    }
}