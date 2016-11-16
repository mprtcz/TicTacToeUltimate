import {Injectable} from "@angular/core";
import {Headers} from "@angular/http";

@Injectable()
export class CredentialsService {
    public isAuthenticated: boolean;
    private credentialsHeader: Headers;

    createAuthHeader(username: string, password: string): void {
        this.credentialsHeader = new Headers({
            'authorization': 'Basic '
            + btoa(username + ':' + password)
        });
        this.isAuthenticated = true;
    }

    getHeader(): string {
        return this.credentialsHeader;
    }

    clearCredentials(): void {
        this.isAuthenticated = false;
        this.credentialsHeader = null;
    }
}