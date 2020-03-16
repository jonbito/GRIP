import client from '../../client';

const state = {
    role: 'Software Developer',
};

const mutations = {
    setRole(state, data) {
        state.role = data;
    },
};

const actions = {
    getFrequentProjects({commit}) {
        commit('setFrequentProjectsLoading', true);
        client.get('/project-menu/frequent').then((response) => {
            commit('setFrequentProjects', response.data);
        }).catch((error) => {
            console.log(error);
        }).then(() => {
            commit('setFrequentProjectsLoading', false);
        })
    }
};

export default {
    namespaced: true,
    state,
    actions,
    mutations,
}
