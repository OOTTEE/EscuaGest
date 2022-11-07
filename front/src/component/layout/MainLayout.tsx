import './MainLatout.css'
import {Outlet, useLocation} from "react-router-dom";
import {Breadcrumb, Layout} from "antd";
import {Content, Footer, Header} from "antd/lib/layout/layout";
import {Sidebar, Views} from "../sidebar/Sidebar";
import Title from "antd/lib/typography/Title";

const breadcrumb = () => {
    return (
        <Breadcrumb style={{margin: '16px 0'}}>
            <Breadcrumb.Item>Home</Breadcrumb.Item>
            <Breadcrumb.Item>List</Breadcrumb.Item>
            <Breadcrumb.Item>App</Breadcrumb.Item>
        </Breadcrumb>
    )
}

export const MainLayout = () => {
    let path = useLocation().pathname
    return (
        <Layout style={{minHeight: '100vh', flexDirection: 'row'}}>
            <Sidebar/>
            <Layout className="site-layout">
                <Header className="site-layout-background" style={{padding: 0, paddingLeft: 24, paddingTop: 4}}>
                    <Title>{Views
                        .filter(value => value.path === path)
                        .map(value => value.title)
                        .pop()}</Title>
                </Header>
                <Content style={{margin: '0 16px'}}>
                    {breadcrumb()}
                    <div className="site-layout-background" style={{padding: 24, minHeight: 360}}>
                        <Outlet/>
                    </div>
                </Content>
                <Footer style={{textAlign: 'center'}}>CN Escualos</Footer>
            </Layout>
        </Layout>
    )
}