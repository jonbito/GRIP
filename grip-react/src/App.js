import React, {Component} from 'react';
import {Route, Switch, Redirect} from 'react-router-dom';

import AppLayout from "./layout/AppLayout";
import Projects from "./Projects";
import ProjectLayout from "./layout/ProjectLayout";

export default class App extends Component {
    static displayName = App.name;

    render() {
        return (
            <Switch>
                <Route exact path="/">
                    <Redirect to="/projects"/>
                </Route>
                <AppLayout path="/projects" component={Projects}/>
                <Route component={Projects}/>
            </Switch>
        );
    }
}
