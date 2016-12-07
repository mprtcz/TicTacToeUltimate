import {OnInit, OnChanges, SimpleChanges, AfterViewInit} from "@angular/core";
import {Directive} from "@angular/core";
declare var componentHandler: any;

@Directive({
    selector: '[mdl-upgrade]'
})
export class MaterialDesignUpgradeElement implements OnInit, AfterViewInit {

    ngAfterViewInit(): void {
        console.log('MDL ngAfterViewInit');
        componentHandler.upgradeDom();
    }

    ngOnInit(): void {
        console.log('MDL on init');
        componentHandler.upgradeDom();
    }
}