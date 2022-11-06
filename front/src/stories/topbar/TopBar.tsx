import React from 'react';

import './Topbar.css';
import {Header} from "antd/lib/layout/layout";

interface HeaderProps {
    user?: any;
}

export const TopBar = ({user}: HeaderProps) => {
    return (
        <Header className="site-layout-background" style={{ padding: 0 }} />
    );
};
