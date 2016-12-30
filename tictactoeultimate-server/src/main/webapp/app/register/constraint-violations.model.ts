export class ConstraintViolations {
    ssoid: string;
    nickname: string;
    password: string;
    email: string;
    role: string;

    constructor() {
        this.ssoid = '';
        this.nickname = '';
        this.password = '';
        this.email = '';
        this.role = '';
    }

    isEmpty(): boolean {
        if (this.ssoid == '' || this.ssoid == null) {
            if (this.nickname == '' || this.nickname == null) {
                if (this.password == '' || this.password == null) {
                    if (this.email == '' || this.email == null) {
                        if (this.role == '' || this.role == null) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}