import {Log, User, UserManager} from "oidc-client";
import React from "react";
import {NavigateFunction, useNavigate} from "react-router-dom";

export class AuthService {
    userManager: UserManager
    user: User | undefined | null = null

    constructor() {
        this.userManager = new UserManager({
            authority: 'http://localhost:8085/realms/Escuagest',
            client_id: 'escuagest-front',
            popup_redirect_uri: 'http://localhost:3000/login_success',
            redirect_uri: 'http://localhost:3000/',
            post_logout_redirect_uri: 'http://localhost:3000/login',
        })
        this.userManager.getUser()
            .then(value => this.user = value)
        Log.level = Log.INFO
        Log.logger = console
    }

    public isLoggedIn() {
        return this.userManager.getUser()
            .then(user => user != null )
    }

    public signinPopupCallback() {
        return this.userManager.signinPopupCallback(window.location.href)
    }

    public signinSilent() {
        return this.userManager.signinSilent()
    }

    public signinPopup() {
        return this.userManager.signinPopup()
    }

    public logout() {
        return this.userManager.signoutRedirect()
    }

}


export const LoginSuccess = () => {
    new AuthService().signinPopupCallback()
    return <></>
}