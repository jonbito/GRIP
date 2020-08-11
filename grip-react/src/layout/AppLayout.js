import React from 'react';
import {Route} from "react-router";
import {Layout} from "antd";

import Header from "./components/Header";

const AppLayout = ({children}) => {
    return (
        <Layout style={{minHeight: '100vh'}}>
            <Header/>
            <Layout>
                <Layout.Content
                    style={{
                        padding: '0 24px 24px 24px',
                        margin: 0,
                        minHeight: 280,
                        background: '#fff'
                    }}
                >
                    {children}
                </Layout.Content>
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
