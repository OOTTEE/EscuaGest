import {AuthResponse} from "./auth.response";

const TOKEN_ENDPOINT = "/realms/Escuagest/protocol/openid-connect/token"
const CLIENT_ID = "escuagest-api"
const GRANT_TYPE = "password"
const CLIENT_SECRET = "mQF5KI9VObIofQwFQYG846a4dAO4GSuN"

export const authRepository = {

    login: (user: string, password: string) => {
        let formBody =
            "client_id=" + CLIENT_ID + "&" +
            "username=" + user + "&" +
            "password=" + password + "&" +
            "grant_type=" + GRANT_TYPE + "&" +
            "client_secret=" + CLIENT_SECRET;

        fetch(TOKEN_ENDPOINT, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: formBody
        }).then(value => value.json() )
        .then(data => data as AuthResponse)

    }

}