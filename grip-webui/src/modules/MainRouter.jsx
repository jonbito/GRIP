import PropTypes from 'prop-types';
import React, { Component } from 'react';
import { BrowserRouter, Switch, Route } from "react-router-dom";
import App from './App';
import HomePage from '../pages/HomePage';
import SettingsPage from '../pages/SettingsPage';

export default class MainRouter extends Component {
    constructor() {
        super();
        this.state = {
            navOpenState: {
                isOpen: true,
                width: 304,
            }
        }
    }

    getChildContext () {
        return {
            navOpenState: this.state.navOpenState,
        };
    }

    appWithPersistentNav = () => (props) => (
        <App
            onNavResize={this.onNavResize}
            {...props}
        />
    )

    onNavResize = (navOpenState) => {
        this.setState({
            navOpenState,
        });
    }

    render() {
        return (
            <BrowserRouter>
                <App>
                    <Switch>
                        <Route path="/" component={HomePage} />
                        <Route path="/settings" component={SettingsPage} />
                    </Switch>
                </App>
            </BrowserRouter>
        );
    }
}

MainRouter.childContextTypes = {
    navOpenState: PropTypes.object,
}
