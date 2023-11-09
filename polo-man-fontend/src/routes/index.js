import { AdminLogin } from "../layout/admin";
import { AdminLayout } from "../layout/admin";
import { AdminDashBoard } from "../components/admin/Dashboard";
import { AdminGuard, UserAuthGuard } from "./guards";
import { UserAccount } from "../components/user/UserAccount";
import {
  AddChatLieu,
  ChatLieuList,
  UpdateChatLieu,
  OrderList,
} from "../components/admin";
import { UserLoginPage, UserProductDetail } from "../components/user";
import { HomePage } from "../components/user/HomePage";
import { NotFoundPage } from "../components/common";
import { UserCart } from "../components/user/Cart";
import { Checkout } from "../components/user/Checkout";
import { UserOrderList } from "../components/user/UserOrderList";
import { UserLayout } from "../layout/user/UserLayout";
const NoGuard = ({ children }) => {
  return <>{children}</>;
};

const getAdminRoute = ({ path, component }) => {
  return {
    path,
    component,
    layout: AdminLayout,
    guard: AdminGuard,
  };
};

const getUserRoute = ({ path, component, guard }) => {
  return {
    path,
    component,
    layout: UserLayout,
    guard: guard || NoGuard,
  };
};

const adminRoutes = [
  {
    path: "admin/login",
    component: AdminLogin,
  },
  getAdminRoute({
    path: "/admin",
    component: AdminDashBoard,
  }),
  getAdminRoute({ path: "admin/chatlieu", component: ChatLieuList }),
  getAdminRoute({ path: "/admin/chatlieu/add", component: AddChatLieu }),
  getAdminRoute({
    path: "/admin/chatlieu/update/:machatlieu",
    component: UpdateChatLieu,
  }),
  getAdminRoute({ path: "/admin/orders", component: OrderList }),
  getUserRoute({ path: "/login", component: UserLoginPage }),
  getUserRoute({ path: "/", component: HomePage }),
  getUserRoute({ path: "/sanpham/:masanpham", component: UserProductDetail }),
  getUserRoute({ path: "/carts", component: UserCart, guard: UserAuthGuard }),
  getUserRoute({
    path: "/account",
    component: UserAccount,
    guard: UserAuthGuard,
  }),
  getUserRoute({
    path: "/orders",
    component: UserOrderList,
    guard: UserAuthGuard,
  }),
  getUserRoute({
    path: "/checkout",
    component: Checkout,
    guard: UserAuthGuard,
  }),
  {
    path: "*",
    component: NotFoundPage,
  },
];

const privateRoutes = [];

export { adminRoutes, privateRoutes };
