<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" routerLink="/login">Header</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a routerLink="/greeting">Greeting</a></li>
                <li><a routerLink="/login">Login</a></li>
                <li><a routerLink="/login">Login</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li *ngIf="getUser() == null">
                    <a routerLink="/login"><span class="glyphicon glyphicon-question-sign"></span>Guest</a>
                </li>
                <li *ngIf="getUser() != null">
                    <a routerLink="/greeting"><span class="glyphicon glyphicon-user"></span>Logged as: {{getUser()}}</a>
                </li>
            </ul>
        </div>
    </div>
</nav>