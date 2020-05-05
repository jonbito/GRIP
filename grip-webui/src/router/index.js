import Vue from 'vue'
import VueRouter from 'vue-router'
import BrowseProjects from '../views/BrowseProjects.vue'
import CreateProject from "../views/CreateProject";
import Project from "../views/Project";
import client from "../client";
import NotFound from "../views/NotFound";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Browse Projects',
    component: BrowseProjects
  },
  {
    path: '/-/projects/new',
    name: 'Create Project',
    component: CreateProject
  },
  {
    path: '/-/404',
    name: '404',
    component: NotFound
  },
  {
    path: '/:username/:projectKey',
    name: 'Project',
    component: Project,
      props: to => to.meta.props,
    beforeEnter: (to, from, next) => {
      client.get('/project/hasAccess?username=' + to.params.username + '&projectKey=' + to.params.projectKey).then(response => {
          to.meta.props = {
            projectId: response.data
          };
          next();
      }).catch(() => {
        next('/-/404');
      });
    },
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
