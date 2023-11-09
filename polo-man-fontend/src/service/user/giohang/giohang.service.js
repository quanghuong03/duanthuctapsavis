import { userAuthService } from "../auth";
import { userClient } from "../http";

export const addProduct = async (makhachhang, form) => {
  return await userClient.post(
    `http://localhost:8080/giohangchitiet/add/${makhachhang}`,
    form
  );
};

export const getProducts = async () => {
  const { makhachhang } = userAuthService.getAuthInfo();
  return await userClient.get(`http://localhost:8080/giohang/${makhachhang}`);
};

export const getProductsByTrangThai = async () => {
  const { makhachhang } = userAuthService.getAuthInfo();
  return await userClient.get(
    `http://localhost:8080/giohang/order/${makhachhang}`
  );
};

export const updateStatus = async (magiohangchitiet, trangthai) => {
  return await userClient.put(
    `http://localhost:8080/giohangchitiet/status/${magiohangchitiet}`,
    {
      trangthai,
    }
  );
};

export const updatesoluong = async (magiohangchitiet, soluong) => {
  return await userClient.put(
    `http://localhost:8080/giohangchitiet/soluong/${magiohangchitiet}`,
    {
      soluong,
    }
  );
};
