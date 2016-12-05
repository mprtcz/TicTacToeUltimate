import {Component, OnInit} from "@angular/core";
import {NewUser} from "./NewUser";
import {RegisterService} from "./register.service";
import {ConstraintViolation} from "./ConstraintViolation";
import {ConstraintViolations} from "./ConstraintViolations";
import {Router} from "@angular/router";
import {EditUserService} from "../shared/edit-user.service";
import {User} from "../login/user";

@Component({
    moduleId: module.id,
    selector: 'app-register',
    templateUrl: './register.component.jsp',
    providers: [RegisterService]
})
export class RegisterComponent implements OnInit {
    private newUser: NewUser;
    private passwordConf: string = '';
    private constraintViolationsObj: ConstraintViolations;
    private message: string = '';
    private isAdmin: boolean;
    private headlineText: string;
    private isEditing: boolean;
    private loggedInUser: User;

    ngOnInit(): void {
        if (this.editUserService.getUser() == null) {
            this.newUser = new NewUser();
            this.newUser.role = 'INIT';
            this.headlineText = 'Register';
            this.isEditing = false;
        } else {
            this.newUser = this.editUserService.getUser();
            this.headlineText = 'Edit profile: ' + this.newUser.ssoId;
            this.isEditing = true;
            this.loggedInUser = JSON.parse(localStorage.getItem("currentUser"));
            this.isAdmin = this.loggedInUser.role == 'ROLE_ADMIN';
        }
    }

    constructor(private registerService: RegisterService,
                private router: Router,
                private editUserService: EditUserService) {
        this.constraintViolationsObj = new ConstraintViolations();
    }

    submit() {
        console.log('user = ' + JSON.stringify(this.newUser));
        this.registerService.registerNewUser(this.newUser).toPromise()
            .then(res => {
                console.log(JSON.stringify(res));
                this.newUser = '';
                this.passwordConf = '';
                this.message = 'Successfully Registered';
                this.router.navigate(['/greeting']);
            }).catch((error: any) => {
                if (error.status == 400) {
                    this.parseConstraintViolations(JSON.parse(error._body));
                } else {
                    console.log(JSON.stringify(error));
                }
            }
        );
    }

    updateUser() {
        this.registerService.updateUser(this.newUser).toPromise()
            .then(res => {
                console.log('Updated User: ' + this.newUser);
                this.message = 'User ' + this.newUser.ssoId + ' updated!';
                this.newUser = '';
                this.passwordConf = '';
                this.editUserService.setUser(null);
            }).catch((error: any) => {
            if (error.status == 400) {
                this.parseConstraintViolations(JSON.parse(error._body));
            } else {
                console.log(JSON.stringify(error));
            }
        });
    }

    validateAndSubmit() {
        console.log('user to update ' +JSON.stringify(this.newUser));
        if (this.isEditing) {
            if (this.newUser.password != null && this.newUser.password != '') {
                if (this.newUser.password != this.passwordConf) {
                    this.constraintViolationsObj.password = 'Passwords do not match';
                    return;
                }
            }
            //this.updateUser();
        } else {
            console.log('validating');
            this.constraintViolationsObj = new ConstraintViolations();
            if (this.newUser.ssoId == '' || this.newUser.ssoId == null) {
                this.constraintViolationsObj.ssoid = 'Username cannot be blank';
            }
            if (this.newUser.nickname == '' || this.newUser.nickname == null) {
                this.constraintViolationsObj.nickname = 'Nickname cannot be blank';
            }
            if (this.newUser.password == '' || this.newUser.nickname == null) {
                this.constraintViolationsObj.password = 'Password cannot be blank';
            }
            if (this.newUser.email == '' || this.newUser.nickname == null) {
                this.constraintViolationsObj.email = 'Email cannot be blank';
            } else if (this.newUser.email.indexOf("@") == -1) {
                this.constraintViolationsObj.email = 'Wrong Email form';
            }
            if (this.newUser.password != this.passwordConf) {
                this.constraintViolationsObj.password = 'Passwords do not match';
            }
            if (this.constraintViolationsObj.isEmpty()) {
                console.log('submitting: ' + JSON.stringify(this.constraintViolationsObj));
                this.submit();
            }
        }
    }

    private parseConstraintViolations(constraintViolations: ConstraintViolation[]) {
        this.constraintViolationsObj = new ConstraintViolations();
        for (let i = 0; i < constraintViolations.length; i++) {
            if (constraintViolations[i].property.toLowerCase() == 'ssoid') {
                this.constraintViolationsObj.ssoid += constraintViolations[i].message + ' ';
            }
            if (constraintViolations[i].property.toLowerCase() == 'nickname') {
                this.constraintViolationsObj.nickname += constraintViolations[i].message + ' ';
            }
            if (constraintViolations[i].property.toLowerCase() == 'password') {
                this.constraintViolationsObj.password += constraintViolations[i].message + ' ';
            }
            if (constraintViolations[i].property.toLowerCase() == 'email') {
                this.constraintViolationsObj.email += constraintViolations[i].message + ' ';
            }
        }
    }
}