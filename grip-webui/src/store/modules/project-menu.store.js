const state = {
    searchLoading: false,
    frequentProjects: [],
};

const mutations = {
    setSearchLoading(state, data) {
        state.searchLoading = data;
    },
};

const actions = {
    type({dispatch, commit, getters, rootGetters}, text) {
        if(text === '') {
            commit('setSearchLoading', false);

        }
        commit('setSearchLoading', true);
    }
}

export default {
    namespaced: true,
    state,
    mutations,
}
