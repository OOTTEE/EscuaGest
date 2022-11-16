import React from 'react';
import './App.css';
import {Router} from "./component/router";
import {BrowserRouter} from "react-router-dom";

export const App = () => {
    return (
        <BrowserRouter>
            <Router/>
        </BrowserRouter>
    );
}
