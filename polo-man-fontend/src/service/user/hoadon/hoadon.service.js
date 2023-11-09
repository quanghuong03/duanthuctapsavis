import { userClient } from "../http";
import { userAuthService } from "../auth";

export const addOrder = async (makhachhang, form) => {
  return await userClient.post(
    `http://localhost:8080/hoadon/add/${makhachhang}`,
    form
  );
};

export const getHoaDon = async (params) => {
  const { makhachhang } = userAuthService.getAuthInfo();
  return await userClient.get(`http://localhost:8080/hoadon/${makhachhang}`, {
    params,
  });
};

export const cancelOrder = async (mahoadon, { ghichu }) => {
  return await userClient.put(`http://localhost:8080/hoadon/${mahoadon}`, {
    ghichu,
  });
};
export const changeStatusOrder = async (mahoadon, trangthai) => {
  return await userClient.put(
    `http://localhost:8080/hoadon/status/${mahoadon}`,
    {
      trangthai: trangthai,
    }
  );
};
