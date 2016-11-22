<div class="row">
    <div class="col-sm-4"></div>
    <div class="col-sm-4">
        <div class="well well-lg">
            <h1>Greetings! :)</h1>
            <br/>
        </div>
        <div class="panel panel-default text-center">
            <div class="panel-heading">
                <button type="button" class="btn btn-info" (click)="getSimpleGreeting()">Get Simple Greeting</button>
            </div>
            <div class="panel-body">
                <input [(ngModel)]="variable" placeholder="variable"/><br/><br/>
                <button type="button" class="btn btn-primary" (click)="getPersonalizedGreeting()">Get Variable Greeting
                </button>
            </div>
        </div>
        <div *ngIf="message">
            <h2>Server response: {{message | uppercase}}</h2>
        </div>
    </div>
</div>