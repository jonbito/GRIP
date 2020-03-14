const state = {
    token: null
};

const mutations = {
    setToken(state, { token }) {
        state.token = token;
    },
};

export default {
    namespaced: true,
    state,
    mutations,
}
