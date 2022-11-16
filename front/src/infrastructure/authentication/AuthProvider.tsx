import React, {useEffect, useState} from "react";
import {AuthService} from "./AuthService";
import {Navigate, useNavigate} from "react-router-dom";

type Props = {
    children?: React.ReactElement
}
type AuthState = {
    authService: AuthService
}

const authState: AuthState = {
    authService: new AuthService()
}

export const AuthContext = React.createContext(authState)

export const AuthProvider = ({children}: Props) => {
    const authService = new AuthService();
    const navigate = useNavigate()

    authService.isLoggedIn()
        .then(value => {
            if(!value) {
                navigate('/login')
            }
        })

    return (
        <AuthContext.Provider value={authState}>
            {children}
        </AuthContext.Provider>
    )
}
