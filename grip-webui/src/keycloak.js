import Keycloak from "keycloak-js";

export default Keycloak({
    url: 'http://localhost:8888/auth',
    realm: 'Grip',
    clientId: 'grip-webui',
});
