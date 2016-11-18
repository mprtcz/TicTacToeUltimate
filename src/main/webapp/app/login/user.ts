export class User {
    ssoId: string;
    nickname: string;
    email: string

    getNickname() : string {
        return this.nickname;
    }
}