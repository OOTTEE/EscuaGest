import './Sidebar.css'
import React, {useState} from "react";
import Sider from "antd/lib/layout/Sider";
import {Image, Menu, MenuProps} from "antd";
import {DeleteRowOutlined, SettingOutlined, TeamOutlined, UserOutlined} from "@ant-design/icons";
import logo from "../../assets/logo_escualos.svg";
import classNames from "classnames";
import {Link} from "react-router-dom";

type MenuItem = Required<MenuProps>['items'][number];

const getItem = (label: React.ReactNode, key: React.Key, icon?: React.ReactNode, children?: MenuItem[]): MenuItem => {
    return {key, icon, children, label} as MenuItem;
}

const getItemLink = (title: string, link: string | null, key: React.Key, icon?: React.ReactNode, children?: MenuItem[]): MenuItem => {
    return getItem(
        link !== null ?<Link to={link}>title</Link>: title,
        key,
        link !== null ?<Link to={link}>{icon}</Link>: icon,
        children
    )
}


export const Sidebar = () => {
    const [collapsed, setCollapsed] = useState(true);
    const items: MenuItem[] = [
        getItemLink('Competiciones', '/competitions', '1', <DeleteRowOutlined style={{fontSize: collapsed ? '16px' : '14px'}}/>),
        getItemLink('Perfil', '/profile', '2', <UserOutlined style={{fontSize: collapsed ? '16px' : '14px'}}/>),
        getItemLink('Administracion', null,'3', <SettingOutlined style={{fontSize: collapsed ? '16px' : '14px'}}/>, [
            getItemLink('Equipo', '/admin/team', 'sub3-1', <TeamOutlined style={{fontSize: collapsed ? '16px' : '14px'}}/>),
        ])
    ];
    const expandMenuToOpenSubmenu:MenuProps['onOpenChange'] = keys => {
        if(keys.length > 0 && collapsed){
            setCollapsed(false)
        }
    }
    return (
        <Sider collapsible collapsed={collapsed} onCollapse={value => setCollapsed(value)}>
            <Image className="logo" src={logo} preview={false}/>
            <Menu theme="dark"
                  defaultSelectedKeys={['1']}
                  mode="inline"
                  items={items}
                  className={classNames({'collapsed-menu': collapsed})}
                  onOpenChange={expandMenuToOpenSubmenu}
            />
        </Sider>
    )
}