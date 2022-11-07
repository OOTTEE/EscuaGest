import React from 'react';
import './App.css';
import {useRoutes} from "react-router-dom";
import {Login} from "./page/Login";
import {Dashboard} from "./page/Dashboard";
import {ProtectedRoute} from "./component/ProtectedRoute";
import {MainLayout} from "./component/layout/MainLayout";
import {CompeticionesDashboard} from "./component/competiciones-dashboard/CompeticionesDashboard";

export const App = () => {
    let routes = useRoutes([
        {
            path: "/",
            element: <ProtectedRoute user={true}>
                <MainLayout/>
            </ProtectedRoute>,
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
                },
            ]
        },
        {
            path: "/login",
            element: <Login/>
        }
    ])

    return (
        <div className="App">
            {routes}
        </div>
    );
}
