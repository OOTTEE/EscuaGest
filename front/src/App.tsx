import React from 'react';
import 'antd/dist/antd.css';
import './App.css';
import {useRoutes} from "react-router-dom";
import {Login} from "./page/Login";
import {Dashboard} from "./page/Dashboard";
import {ProtectedRoute} from "./component/ProtectedRoute";
import {MainLayout} from "./stories/layout/MainLayout";

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
                    element: <h2>Competitions</h2>
                },
                {
                    path: "/profile",
                    element: <h2>Profile</h2>
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
