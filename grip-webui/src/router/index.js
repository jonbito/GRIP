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
    path: '/projects/new',
    name: 'Create Project',
    component: CreateProject
  },
  {
    path: '/404',
    name: '404',
    component: NotFound
  },
  {
    path: '/:userName/:projectKey',
    name: 'Project',
    component: Project,
    beforeEnter: (to, from, next) => {
      client.get('/project/hasAccess?username=' + to.params.userName + '&projectKey=' + to.params.projectKey).then(() => {
        next();
      }).catch(() => {
        next('/404');
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
