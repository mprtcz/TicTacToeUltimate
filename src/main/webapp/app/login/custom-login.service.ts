import {Injectable} from "@angular/core";

@Injectable()
export class CustomLoginService {
    private username: string;
    private isAuthenticated: boolean;

    constructor() {
        this.isAuthenticated = false
    };

    getUser(): void {
        return this.username;
    }

    isAuthenticated(): boolean {
        return this.isAuthenticated;
    }

    setUser(username: string): void {
        this.username = username;
        this.isAuthenticated = true;
    }

    clearUser(): void {
        this.username = '';
        this.isAuthenticated = false;
    }
}