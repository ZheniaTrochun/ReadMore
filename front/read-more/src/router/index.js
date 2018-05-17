import Vue from 'vue'
import Router from 'vue-router'

import Main from '@/components/Main'
import Admin from '@/components/Admin'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Main',
      component: Main
    },
    {
      path: '/admin',
      name: 'Admin',
      component: Admin
    }
  ]
})
