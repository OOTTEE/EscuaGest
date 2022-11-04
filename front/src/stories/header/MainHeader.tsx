import React from 'react';

import './header.css';
import {Menu, MenuProps} from "antd";
import {Header} from "antd/lib/layout/layout";

interface HeaderProps {
    user?: any;
}

const items1: MenuProps['items'] = ['1', '2', '3'].map(key => ({
    key,
    label: `nav ${key}`,
}));

export const MainHeader = ({user}: HeaderProps) => {
    return (
        <Header className="header">
            <div className="logo" />
            <Menu theme="dark" mode="horizontal" defaultSelectedKeys={['2']} items={items1} />
        </Header>
    );
};
