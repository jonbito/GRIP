import React, {Component} from 'react';
import {Route} from "react-router-dom";
import {Layout, Menu, Breadcrumb} from "antd";
import {
    BarsOutlined,
    StockOutlined,
    SettingTwoTone
} from "@ant-design/icons"
import Header from "./components/Header";
const { Content, Sider } = Layout;

const AppLayout = ({children}) => {

    return (
        <Layout style={{minHeight: '100vh'}}>
            <Header />
            <Layout>
                <Sider breakpoint="md">
                    <Menu
                        mode="inline"
                        defaultSelectedKeys={['1']}
                        defaultOpenKeys={['sub1']}
                        style={{ height: '100%', borderRight: 0 }}
                    >
                        <Menu.Item key="1" icon={<BarsOutlined />}>Backlog</Menu.Item>
                        <Menu.Item key="2" icon={<StockOutlined />}>Reports</Menu.Item>
                        <Menu.Divider />
                        <Menu.Item key="3" icon={<SettingTwoTone />}>Project Settings</Menu.Item>
                    </Menu>
                </Sider>
                <Layout style={{ padding: '0 24px 24px' }}>
                    <Breadcrumb style={{ margin: '16px 0' }}>
                        <Breadcrumb.Item>Home</Breadcrumb.Item>
                        <Breadcrumb.Item>List</Breadcrumb.Item>
                        <Breadcrumb.Item>App</Breadcrumb.Item>
                    </Breadcrumb>
                    <Content
                        style={{
                            padding: 24,
                            margin: 0,
                            minHeight: 280,
                            background: '#fff'
                        }}
                    >
                        {children}
                    </Content>
                </Layout>
            </Layout>
        </Layout>
    );
};

export default ({component: Component, ...rest}) => (
    <Route {...rest} render={matchProps => (
        <AppLayout {...matchProps}>
            <Component {...matchProps} />
        </AppLayout>
    )}/>
);
