import React from "react";
import './MainLatout.css'
import {Outlet} from "react-router-dom";
import {Breadcrumb, Layout} from "antd";
import {Content, Footer} from "antd/lib/layout/layout";
import {MainHeader} from "../header/MainHeader";
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
        <>
            <Layout>
                <MainHeader/>
                <Content style={{padding: '0 50px'}}>
                    {breadcrumb()}
                    <Layout className="site-layout-background" style={{padding: '24px 0'}}>
                        <Sidebar/>
                        <Content style={{padding: '0 24px', minHeight: 280}}>
                            <Outlet/>
                        </Content>
                    </Layout>
                </Content>
                <Footer style={{textAlign: 'center'}}>CN Escualos</Footer>
            </Layout>
        </>
    )
}