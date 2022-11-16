import {useContext} from "react";
import {AuthContext} from "../authentication/AuthProvider";
import {AuthService} from "../authentication/AuthService";

const headers = {
    Accept: 'application/json',
    'Content-Type': 'application/json',
    'Accept-Language': 'ES'
};

export const useHttpClient = () => {
    const authContext = useContext(AuthContext)
    return new HttpClient(authContext.authService);
}

class HttpClient {
    authService: AuthService

    constructor(authService: AuthService) {
        this.authService = authService
    }

    public get = async <T extends unknown>(url: string) => {
        return await this.authService.userManager.getUser().then(user => {
            console.log(user?.id_token)
            console.log(user)
            const tokenHeader = {
                Authorization: "Bearer " + user?.id_token
            }
            return fetch(url, {
                method: 'GET',
                headers: {
                    ...headers,
                    ...tokenHeader
                },
                cache: "no-cache",
            }).then(response => {return (response.json()) as T;})
        })
    }

    public post = async <T extends unknown>(url: string, body: any) => {
        const response = await fetch(url, {
            method: 'POST',
            headers,
            body
        });
        return (await response.json()) as T;
    }

    public put = async <T extends unknown>(url: string, body: any) => {
        const response = await fetch(url, {
            method: 'PUT',
            headers,
            body
        });
        return (await response.json()) as T;
    }

    public _delete = async <T extends unknown>(url: string) => {
        const response = await fetch(url, {
            method: 'DELETE',
            headers
        });
        return response.ok;
    }

}
