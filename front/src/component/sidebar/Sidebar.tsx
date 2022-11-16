import './Sidebar.css'
import React, {useState} from "react";
import Sider from "antd/lib/layout/Sider";
import {Image, Menu, MenuProps} from "antd";
import {GoldOutlined, LogoutOutlined, SettingOutlined, TeamOutlined, UserOutlined} from "@ant-design/icons";
import logo from "../../assets/logo_escualos.svg";
import classNames from "classnames";
import {Link, useNavigate} from "react-router-dom";
import {AuthContext} from "../../infrastructure/authentication/AuthProvider";


type MenuItem = Required<MenuProps>['items'][number];


export const Views = [
    {
        path: '/competitions',
        title: 'Competiciones',
        linkable: true
    },
    {
        path: '/profile',
        title: 'Perfil',
        linkable: true
    },
    {
        path: '/admin',
        title: 'Administración',
        linkable: false
    },
    {
        path: '/admin/team',
        title: 'Equipo',
        linkable: true
    },
    {
        path: '/logout',
        title: 'Logout',
        linkable: true
    }
]


const getItem = (label: React.ReactNode, key: React.Key, icon?: React.ReactNode, children?: MenuItem[]): MenuItem => {
    return {key, icon, children, label} as MenuItem;
}

const getItemLink = (link: string | null, key: React.Key, icon?: React.ReactNode, children?: MenuItem[]): MenuItem => {
    let title = Views.filter(value => value.path === link)
        .map(value => value.title).pop()
    let linkable = Views.filter(value => value.path === link)
        .map(value => value.linkable).pop()
    return getItem(
        link !== null && linkable ? <Link to={link}>{title}</Link> : title,
        key,
        link !== null && linkable ? <Link to={link}>{icon}</Link> : icon,
        children
    )
}

export const Sidebar = () => {
    const [collapsed, setCollapsed] = useState(true);
    const navigate = useNavigate();
    const iconClassNames = () => {
        return collapsed ? 'collapsed-menu-icon' : 'menu-icon';
    }
    const { authService } = React.useContext(AuthContext);
    const logout = () => {
        authService.logout()
    }
    const items: MenuItem[] = [
        getItemLink('/competitions', '1', <GoldOutlined className={iconClassNames()} />),
        getItemLink('/profile', '2', <UserOutlined className={iconClassNames()} />),
        getItemLink('/admin', '3', <SettingOutlined className={iconClassNames()} />,[
            getItemLink('/admin/team', 'sub3-1', <TeamOutlined className={iconClassNames()} />)
        ]),
        getItem(<Link to={'/logout'} onClick={logout}>Logout</Link>, '4', <Link to={'/logout'} onClick={logout}><LogoutOutlined className={iconClassNames()} /></Link> )
    ];
    const expandMenuToOpenSubmenu: MenuProps['onOpenChange'] = keys => {
        if (keys.length > 0 && collapsed) {
            setCollapsed(false)
        }
    }
    return (
        <Sider collapsible collapsed={collapsed} onCollapse={value => {
            setCollapsed(value);
        }}>
            <Image className="logo" src={logo} preview={false} onClick={() => {
                navigate('/')
            }}/>
            <Menu theme="dark"
                  defaultSelectedKeys={[]}
                  mode="inline"
                  items={items}
                  className={classNames({'collapsed-menu': collapsed})}
                  onOpenChange={expandMenuToOpenSubmenu}
            />

        </Sider>
    )
}