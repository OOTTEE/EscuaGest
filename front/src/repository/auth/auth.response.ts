export interface AuthResponse {
    access_token: string,
    expires_in: number,
    refresh_expires_in: number,
    refresh_token: string,
    'not-before-policy': number,
    session_state: string,
    scope: string
}