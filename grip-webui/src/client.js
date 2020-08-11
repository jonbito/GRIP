import axios from 'axios';
import router from './router';

const client = axios.create({
    baseURL: process.env.VUE_APP_API_BASE_URL
});
client.interceptors.response.use(
    response => response,
    error => {
        if(error.response.status === 403) {
            router.replace('/-/404');
        } else if(error.response.status === 404) {
            router.replace('/-/404');
        }
        return Promise.reject(error)
    },
);

export default client;
