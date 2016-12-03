import {OnInit} from "@angular/core";
import {Directive} from "@angular/core";
declare var componentHandler: any;

@Directive({
    selector: '[mdl-upgrade]'
})
export class MaterialDesignUpgradeElement implements OnInit {
    ngOnInit(): void {
        console.log('MDL on init');
        componentHandler.upgradeDom();
    }
}