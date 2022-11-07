import './Sidebar.css'
import {useState} from "react";
import Sider from "antd/lib/layout/Sider";
import {Image, Menu, MenuProps} from "antd";
import {GoldOutlined, SettingOutlined, TeamOutlined, UserOutlined} from "@ant-design/icons";
import logo from "../../assets/logo_escualos.svg";
import classNames from "classnames";
import {Link} from "react-router-dom";

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
    const items: MenuItem[] = [
        getItemLink( '/competitions', '1', <GoldOutlined
            style={{fontSize: collapsed ? '16px' : '14px'}}/>),
        getItemLink('/profile', '2', <UserOutlined style={{fontSize: collapsed ? '16px' : '14px'}}/>),
        getItemLink('/admin', '3', <SettingOutlined style={{fontSize: collapsed ? '16px' : '14px'}}/>, [
            getItemLink('/admin/team', 'sub3-1', <TeamOutlined
                style={{fontSize: collapsed ? '16px' : '14px'}}/>),
        ])
    ];
    const expandMenuToOpenSubmenu: MenuProps['onOpenChange'] = keys => {
        if (keys.length > 0 && collapsed) {
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