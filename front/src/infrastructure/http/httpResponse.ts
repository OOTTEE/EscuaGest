export interface httpResponse {
    bodyUsed: boolean;
    ok: boolean;
    redirected: boolean;
    status: number;
    statusText: string;
    type: string;
    url: string;
    error?: string;
    json: () => string | void | [] | Array<any> | Promise<any>;
}
