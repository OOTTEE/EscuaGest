import {httpResponse} from "./httpResponse";

const headers = {
    Accept: 'application/json',
    'Content-Type': 'application/json',
    'Accept-Language': 'ES'
};

const get = async <T extends unknown>(url: string) => {
    const response: httpResponse = await fetch(url, {
        method: 'GET',
        headers: {
            ...headers,
        },
        cache: "no-cache"
    });
    return (await response.json()) as T;
}

const post = async <T extends unknown>(url: string, body: any) => {
    const response = await fetch(url, {
        method: 'POST',
        headers,
        body
    });
    return (await response.json()) as T;
}

const put = async <T extends unknown>(url: string, body: any) => {
    const response = await fetch(url, {
        method: 'PUT',
        headers,
        body
    });
    return (await response.json()) as T;
}

const _delete = async <T extends unknown>(url: string) => {
    const response = await fetch(url, {
        method: 'DELETE',
        headers
    });
    return response.ok;
}

export default {
    get,
    post,
    put,
    delete: _delete
}