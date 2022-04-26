import { createRouter, createWebHistory } from "vue-router";
import LoginLayout from "@/layout/LoginLayout.vue";
import MainLayout from "@/layout/MainLayout.vue";
import NotFoundView from "@/views/NotFoundView.vue";
import DashView from "@/views/DashView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/login",
      component: LoginLayout,
    },
    {
      path: "/",
      component: MainLayout,
      children: [
        {
          path: "dashboard",
          alias: "",
          component: DashView,
          name: "Dashboard",
          meta: {
            description: "Dashboard view",
          },
        },
      ],
    },
    {
      path: "/:path*",
      component: NotFoundView,
    },
  ],
});

export default router;
