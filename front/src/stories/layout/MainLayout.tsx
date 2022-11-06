import React from "react";
import './MainLatout.css'
import {Outlet} from "react-router-dom";
import {Breadcrumb, Layout} from "antd";
import {Content, Footer, Header} from "antd/lib/layout/layout";
import {Sidebar} from "../sidebar/Sidebar";

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
    return (
        <Layout style={{minHeight: '100vh', flexDirection: 'row'}}>
            <Sidebar/>
            <Layout className="site-layout">
                <Header className="site-layout-background" style={{padding: 0}}/>
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