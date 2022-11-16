import {useRoutes} from "react-router-dom";
import {MainLayout} from "./layout/MainLayout";
import {Dashboard} from "./dashboard/Dashboard";
import {CompeticionesDashboard} from "./competiciones-dashboard/CompeticionesDashboard";
import {Login} from "./login/Login";
import React from "react";
import {AuthProvider} from "../infrastructure/authentication/AuthProvider";
import {LoginSuccess} from "../infrastructure/authentication/AuthService";


export const Router = () => {
    const routes = useRoutes([
        {
            path: "/",
            element: <AuthProvider>
                <MainLayout/>
            </AuthProvider>,
            children: [
                {
                    path: "/",
                    element: <Dashboard/>
                },
                {
                    path: "/competitions",
                    element: <CompeticionesDashboard/>
                },
                {
                    path: "/profile",
                    element: <h2>Profile</h2>
                },
                {
                    path: "/admin/team",
                    element: <h2>Team</h2>
                }
            ]
        },
        {
            path: "/login",
            element: <Login/>
        },
        {
            path: "/login_success",
            element: <LoginSuccess />
        }
    ])

    return (
        <>{routes}</>
    );
}
