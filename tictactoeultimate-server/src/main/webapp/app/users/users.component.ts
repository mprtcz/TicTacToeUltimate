import {Component, OnInit, AfterViewInit, AfterContentInit} from "@angular/core";
import {User} from "../login/user.model";
import {UsersService} from "./users.serivce";

@Component({
    moduleId: module.id,
    selector: 'users-list',
    templateUrl: '/app/users/users.component.jsp',
    providers: [UsersService]
})
export class UsersComponent implements OnInit {
    private message : string;

    ngOnInit(): void {
        this.getUsers();
    }

    private users: User[];

    constructor(private usersService: UsersService) {
    }

    getUsers(): void {
        this.usersService.getUsers()
            .then(res => {
                console.log(JSON.stringify(res));
                this.users = JSON.parse(res._body);
                console.log('parsed users: ' + JSON.stringify(this.users));
            })
            .catch((error: any) => {
                this.message = JSON.stringify(error);
            });
    }

    deleteUser(user: User): void {
        this.users.splice(this.users.indexOf(user), 1);
        this.usersService.deleteUser(user)
            .then(res => {
                console.log(JSON.stringify(res));
            })
            .catch((error: any) => {
                    console.log(JSON.stringify(error));
            });
    }

    updateUser(user: User): void {}

    showUser(user : User) : void {}

    showButtonDialog(user: User): void {
        var self = this;
        var user = user;
        var dialogPolyfill;
        var dialog = document.querySelector('dialog');
        if (!dialog.showModal) {
            dialogPolyfill.registerDialog(dialog);
        }
        dialog.showModal();
        dialog.querySelector('.close').addEventListener('click', function () {
            dialog.close();
        });
        dialog.querySelector('.delete-confirm').addEventListener('click', function () {
            console.log('confirmed');
            dialog.close();
            self.deleteUser(user);
        });
    }
}