import {Component, OnInit} from "@angular/core";
import {NewUser} from "./NewUser";
import {RegisterService} from "./register.service";
import {ConstraintViolation} from "./ConstraintViolation";
import {ConstraintViolations} from "./ConstraintViolations";
import {Router} from "@angular/router";

@Component({
    moduleId: module.id,
    selector: 'app-register',
    templateUrl: './register.component.jsp',
    providers: [RegisterService]
})
export class RegisterComponent implements OnInit {
    private newUser: NewUser;
    private passwordConf: string = '';
    private constraintViolationsObj : ConstraintViolations;
    private message : string = '';
    private variable : boolean = true;

    ngOnInit(): void {
    }

    constructor(private registerService: RegisterService, private router: Router) {
        this.newUser = new NewUser();
        this.newUser.role = 'INIT';
        this.constraintViolationsObj = new ConstraintViolations();
    }

    submit() {
        console.log('user = ' + JSON.stringify(this.newUser));
        this.registerService.registerNewUser(this.newUser).toPromise()
            .then(res => {
                console.log(JSON.stringify(res));
                this.newUser = '';
                this.passwordConf ='';
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

    validateAndSubmit() {
        console.log('validating');
        this.constraintViolationsObj = new ConstraintViolations();
        if(this.newUser.ssoId == '' || this.newUser.ssoId == null){
            this.constraintViolationsObj.ssoid = 'Username cannot be blank';
        }
        if(this.newUser.nickname == '' || this.newUser.nickname == null) {
            this.constraintViolationsObj.nickname = 'Nickname cannot be blank';
        }
        if(this.newUser.password == '' || this.newUser.nickname == null) {
            this.constraintViolationsObj.password = 'Password cannot be blank';
        }
        if(this.newUser.email == '' || this.newUser.nickname == null) {
            this.constraintViolationsObj.email = 'Email cannot be blank';
        } else if (this.newUser.email.indexOf("@") == -1) {
            this.constraintViolationsObj.email = 'Wrong Email form';
        }
        if(this.newUser.password != this.passwordConf) {
            this.constraintViolationsObj.password = 'Passwords do not match';
        }
        if(this.constraintViolationsObj.isEmpty()) {
            console.log('submitting: ' +JSON.stringify(this.constraintViolationsObj));
            this.submit();
        }
    }

    private parseConstraintViolations(constraintViolations :ConstraintViolation[]) {
        this.constraintViolationsObj = new ConstraintViolations();
        for(let i = 0; i < constraintViolations.length; i++){
            if(constraintViolations[i].property.toLowerCase() == 'ssoid') {
                this.constraintViolationsObj.ssoid += constraintViolations[i].message + ' ';
            }if(constraintViolations[i].property.toLowerCase() == 'nickname') {
                this.constraintViolationsObj.nickname += constraintViolations[i].message + ' ';
            }if(constraintViolations[i].property.toLowerCase() == 'password') {
                this.constraintViolationsObj.password += constraintViolations[i].message + ' ';
            }if(constraintViolations[i].property.toLowerCase() == 'email') {
                this.constraintViolationsObj.email += constraintViolations[i].message + ' ';
            }
        }
    }


}