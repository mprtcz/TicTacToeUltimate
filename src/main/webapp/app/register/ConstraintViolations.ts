export class ConstraintViolations {
    ssoid: string;
    nickname: string;
    password: string;
    email: string;

    constructor() {
        this.ssoid = '';
        this.nickname = '';
        this.password = '';
        this.email = '';
    }

    isEmpty(): boolean {
        if (this.ssoid == '' || this.ssoid == null) {
            if (this.nickname == '' || this.nickname == null) {
                if (this.password == '' || this.password == null) {
                    if (this.email == '' || this.email == null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}