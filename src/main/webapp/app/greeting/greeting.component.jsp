<h1>Greetings! :)</h1>
<button (click)="getSimpleGreeting()">Get Simple Greeting</button>
<br/>
<input [(ngModel)]="variable" placeholder="variable"/><br/>
<button (click)="getPersonalizedGreeting()">Get Personalized Greeting</button>
<br/>
<div *ngIf="message">
    <h2>Server response: {{message | uppercase}}</h2>
</div>
