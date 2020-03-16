import debounce from "lodash/debounce";
import client from '../../client';

const state = {
    searchLoading: false,
    searchText: '',
    frequentProjects: [],
    frequentProjectsLoading: false,
    projectsSearchResults: []
};

const mutations = {
    setSearchLoading(state, data) {
        state.searchLoading = data;
    },
    setSearchText(state, data) {
        state.searchText = data;
    },
    setProjectsSearchResults(state, data) {
        state.projectsSearchResults = data;
    },
    setFrequentProjectsLoading(state, data) {
        state.frequentProjectsLoading = data;
    },
    setFrequentProjects(state, data) {
        state.frequentProjects = data;
    }
};

const textDebounce = debounce((commit, text) => {
    client.get('/project-menu/search/' + text).then((response) => {
        commit('setProjectsSearchResults', response.data);
    }).catch((error) => {
        console.log(error);
    }).then(() => {
        commit('setSearchLoading', false);
    });
}, 500);

const actions = {
    type({commit}, text) {

        commit('setSearchText', text);
        if(text === '') {
            commit('setSearchLoading', false);
            commit('setProjectsSearchResults', []);
            textDebounce.cancel();
            return;
        }

        commit('setSearchLoading', true);
        textDebounce(commit, text);
    },
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
