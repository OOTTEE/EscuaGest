import React from 'react';
import 'semantic-ui-css/semantic.min.css'
import './App.css';
import {useRoutes} from "react-router-dom";
import {Main} from "./page/Main";
import {Dashboard} from "./page/Dashboard";
import {ProtectedRoute} from "./component/ProtectedRoute";

export const App = () => {
    let routes = useRoutes([
        {
            path: "/",
            element: <Main/>
        },
        {
            path: "/dashboard",
            element: <ProtectedRoute user={true}>
                        <Dashboard/>
                    </ProtectedRoute>,
            children: [
                {
                    path: "/dashboard/competitions",
                    element: <h2>Competitions</h2>
                },
                {
                    path: "/dashboard/profiles",
                    element: <h2>Profiles</h2>
                },
            ]
        },
    ])

    return (
        <div className="App">
            {routes}
        </div>
    );
}
