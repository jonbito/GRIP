import axios from 'axios';

const client = axios.create({
    baseURL: 'http://localhost:7777'
});
client.interceptors.response.use(
    response => response,
    error => {
        return Promise.reject(error)
    },
);

export default client;
