import axios from 'axios';

const client = axios.create({
    baseURL: 'http://localhost:7777'
});
client.interceptors.response.use(
    response => response,
    (error) => {
        console.log(error);
    }
);

export default client;
