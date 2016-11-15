<h1>Greetings! :)</h1>
<button (click)="getSimpleGreeting()">Get Simple Greeting</button>
<div *ngIf="message">
    <h2>{{message | uppercase}} is my hero</h2>
</div>