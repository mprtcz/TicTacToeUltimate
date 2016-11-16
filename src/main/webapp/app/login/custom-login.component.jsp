<h1>CustomLoginPage</h1>
<form role="form">
    <div class="form-group">
        <label for="username">Username:</label>
        <input type="text" class="form-control" id="username" name="username"
                                                       [(ngModel)]="username"/>
    </div>
    <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" class="form-control" id="password" name="password"
                                                       [(ngModel)]="password"/>
    </div>
    <button type="submit" class="btn btn-primary" (click)="getResponse()">Submit</button>
</form>
<div *ngIf="response">
    <h2>Server response: {{response}}</h2>
</div>