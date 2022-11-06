import './Sidebar.css'
import React, {useState} from "react";
import Sider from "antd/lib/layout/Sider";
import {Image, Menu, MenuProps} from "antd";
import {DeleteRowOutlined, SettingOutlined, TeamOutlined, UserOutlined} from "@ant-design/icons";
import logo from "../../assets/logo_escualos.svg";
import classNames from "classnames";

type MenuItem = Required<MenuProps>['items'][number];

const getItem = (
    label: React.ReactNode,
    key: React.Key,
    icon?: React.ReactNode,
    children?: MenuItem[],
): MenuItem => {
    return {
        key,
        icon,
        children,
        label,
    } as MenuItem;
}


export const Sidebar = () => {
    const [collapsed, setCollapsed] = useState(true);
    const items: MenuItem[] = [
        getItem('Competiciones', '1', <DeleteRowOutlined style={{fontSize: collapsed ? '16px' : '14px'}} />),
        getItem('Profile', '2', <UserOutlined style={{fontSize: collapsed ? '16px' : '14px'}}/>),
        getItem('Administracion', '3', <SettingOutlined style={{fontSize: collapsed ? '16px' : '14px'}}/>, [
            getItem('Team', 'sub3-1', <TeamOutlined style={{fontSize: collapsed ? '16px' : '14px'}}/>),
        ])
    ];

    return (
        <Sider collapsible collapsed={collapsed} onCollapse={value => setCollapsed(value)}>
            <Image className="logo" src={logo} preview={false}/>
            <Menu theme="dark"
                  defaultSelectedKeys={['1']}
                  mode="inline"
                  items={items}
                  className={classNames({'collapsed-menu': collapsed})}
            />
        </Sider>
    )
}