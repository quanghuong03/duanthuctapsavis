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
import { SearchProduct } from "../components/user/SearchProduct";
import { HomePage } from "../components/user/HomePage";
import { NotFoundPage } from "../components/common";
import { UserCart } from "../components/user/Cart";
import { Checkout } from "../components/user/Checkout";
import { UserOrderList } from "../components/user/UserOrderList";
import { UserLayout } from "../layout/user/UserLayout";
import { OrderDetail } from "../components/admin";
import { UpdateOrder } from "../components/admin";
import { ProductList, AddProduct, AddOrder } from "../components/admin";
import { MauSacList } from "../components/admin/MauSac/MauSacList";
import { AddMauSac } from "../components/admin/MauSac/AddMauSac/AddMauSac";
import { UpdateMauSac } from "../components/admin/MauSac/UpdateMauSac/UpdateMauSac";
import { SizeList } from "../components/admin/Size/SIzeList";
import { AddSize } from "../components/admin/Size/AddSize/AddSize";
import { UpdateSize } from "../components/admin/Size/UpdateSize/UpdateSize";
import { DongSPList } from "../components/admin/DongSP/DongSPList";
import { AddDongSp } from "../components/admin/DongSP/AddDongsp/AddDongSP";
import { UpdateDongSp } from "../components/admin/DongSP/UpdateDongSp/UpdateDongSp";
import { ThuongHieuList } from "../components/admin/ThuongHieu/ThuongHieuList";
import { AddThuongHiec } from "../components/admin/ThuongHieu/ThuongHieuAdd/ThuongHieuAdd";
import { UpdateThuongHieu } from "../components/admin/ThuongHieu/ThuongHieuUpdate/ThuongHieuUpdate";
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
  getAdminRoute({ path: "admin/mausac", component: MauSacList }),
  getAdminRoute({ path: "/admin/mausac/add", component: AddMauSac }),
  getAdminRoute({
    path: "/admin/mausac/update/:mamausac",
    component: UpdateMauSac,
  }),
  getAdminRoute({ path: "admin/size", component: SizeList }),
  getAdminRoute({ path: "/admin/size/add", component: AddSize }),
  getAdminRoute({
    path: "/admin/size/update/:masize",
    component: UpdateSize,
  }),
  getAdminRoute({ path: "admin/dongsp", component: DongSPList }),
  getAdminRoute({ path: "/admin/dongsp/add", component: AddDongSp }),
  getAdminRoute({
    path: "/admin/dongsp/update/:madongsp",
    component: UpdateDongSp,
  }),
  getAdminRoute({ path: "admin/thuonghieu", component: ThuongHieuList }),
  getAdminRoute({ path: "/admin/thuonghieu/add", component: AddThuongHiec }),
  getAdminRoute({
    path: "/admin/thuonghieu/update/:mathuonghieu",
    component: UpdateThuongHieu,
  }),
  getAdminRoute({ path: "/admin/orders", component: OrderList }),
  getAdminRoute({ path: "/admin/orders/:mahoadon", component: OrderDetail }),
  getAdminRoute({
    path: "/admin/orders/update/:mahoadon",
    component: UpdateOrder,
  }),
  getAdminRoute({ path: "/admin/orders/add", component: AddOrder }),
  getAdminRoute({ path: "/admin/sanpham", component: ProductList }),
  getAdminRoute({ path: "/admin/sanpham/add", component: AddProduct }),
  getUserRoute({ path: "/login", component: UserLoginPage }),
  getUserRoute({ path: "/", component: HomePage }),
  getUserRoute({ path: "/sanpham/:masanpham", component: UserProductDetail }),
  getAdminRoute({
    path: "/admin/sanpham/update/:masanpham",
    component: AddProduct,
  }),
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
  getAdminRoute({
    path: "/admin/products/update/:productId",
    component: AddProduct,
  }),
  getUserRoute({ path: "/search", component: SearchProduct }),
  {
    path: "*",
    component: NotFoundPage,
  },
];

const privateRoutes = [];

export { adminRoutes, privateRoutes };
