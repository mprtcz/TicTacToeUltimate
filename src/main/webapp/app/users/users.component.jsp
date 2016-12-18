<style>
    .mdl-grid {
        justify-content: center;
        margin-top: 25px;
    }
    .mdl-dialog {
        position: fixed;
        margin-left: 50%;
    }
</style>
<section mdl-upgrade class="section--center tab-content">
    <div class="mdl-grid mdl-grid--no-spacing">
        <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
            <thead>
            <tr>
                <th class="mdl-data-table__cell--non-numeric">SSOID (Username)</th>
                <th>Nickname</th>
                <th>Email</th>
                <th>Role</th>
            </tr>
            </thead>
            <tbody>
            <tr *ngFor="let user of users">
                <td class="mdl-data-table__cell--non-numeric">{{user.ssoId}}</td>
                <td class="mdl-data-table__cell--non-numeric">{{user.nickname}}</td>
                <td class="mdl-data-table__cell--non-numeric">{{user.email}}</td>
                <td class="mdl-data-table__cell--non-numeric">{{user.role.split('_')[1]}}</td>
                <td class="mdl-data-table__cell--non-numeric">
                    <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent"
                    (click)="showButtonDialog(user)" mdl-upgrade >Delete User</button></td>
                <td class="mdl-data-table__cell--non-numeric">
                    <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent"
                    (click)="updateUser(user)" mdl-upgrade>Edit User</button></td>
            </tr>
            </tbody>
        </table>
    </div>
</section>
<dialog class="mdl-dialog">
    <h4 class="mdl-dialog__title">Delete</h4>
    <div class="mdl-dialog__content">
        <p>
            Do you really want to delete this User?
        </p>
    </div>
    <div class="mdl-dialog__actions">
        <button type="button" class="mdl-button delete-confirm">Yes</button>
        <button type="button" class="mdl-button close">No</button>
    </div>
</dialog>