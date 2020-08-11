import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import App from './App';
import registerServiceWorker from './registerServiceWorker';
import keycloak from "./keycloak";
import client from "./client";

const baseUrl = document.getElementsByTagName('base')[0].getAttribute('href');
const rootElement = document.getElementById('root');

const setAxiosHeader = () => {
    client.defaults.headers.common['Authorization'] = 'Bearer ' + keycloak.token;
}

keycloak.init({onLoad: 'login-required'}).then(() => {
    setAxiosHeader();
    
    ReactDOM.render(
        <BrowserRouter basename={baseUrl}>
            <App />
        </BrowserRouter>,
        rootElement);
});

keycloak.onTokenExpired = () => {
    keycloak.updateToken(30).then(() => {
        setAxiosHeader();
    });
};

registerServiceWorker();

