import { createRouter, createWebHistory } from 'vue-router'
import Layout from "@/layout/Layout";

const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: "/login",
    children: [
      {
        path: 'wen_wu',
        name: 'wen_wu',
        component: () => import("@/views/wen_wu"),
      },
      {
        path: 'time_line',
        name: 'time_line',
        component: () => import("@/views/time_line"),
      },
    ]
  },
  {
    path: '/user_info',
    name: 'user_info',
    component: () => import("@/views/user_info")
  },
  {
    path: '/user_info_change',
    name: 'user_info_change',
    component: () => import("@/views/user_info_change")
  },

  {
    path: '/login',
    name: 'login',
    component: () => import("@/views/login")
  },
  {
    path: '/register',
    name: 'register',
    component: () => import("@/views/register")
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
