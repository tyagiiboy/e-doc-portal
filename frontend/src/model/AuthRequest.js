
export default class AuthRequest {
    constructor(
        id = null,
        username = null,
        password = null,
        role = null, token = null
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.token = token
    }
}

