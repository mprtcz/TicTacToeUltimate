<div class="row">
    <div class="col-sm-4"></div>
    <div class="col-sm-4">
        <div class="panel panel-info text-center">
            <div class="panel-heading"><h2>Log In</h2></div>
            <div class="panel-body">
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
                    <button type="submit" class="btn btn-primary" (click)="getData()">Submit</button>
                </form>
                <div *ngIf="message">
                    <h2>{{message}}</h2>
                </div>
            </div>
        </div>
    </div>
</div>
