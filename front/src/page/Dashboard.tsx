import {Header} from "../stories/Header";
import React from "react";
import {Outlet} from "react-router-dom";

export const Dashboard = () => {
    return (
        <div className="dashboard">
            <Header></Header>
            <Outlet />
        </div>
    )
}
