import { adminClient } from "../http";

export const getOrders = async (params) => {
  return await adminClient.get("http://localhost:8080/hoadon", {
    params,
  });
};

export const getOrder = async (mahoadon) => {
  return await adminClient.get(
    `http://localhost:8080/hoadon/admin/${mahoadon}`
  );
};

export const changeStatusOrder = async (mahoadon, trangthai) => {
  return await adminClient.put(
    `http://localhost:8080/hoadon/status/${mahoadon}`,
    {
      trangthai: trangthai,
    }
  );
};

export const changeProfileUserOrder = async (mahoadon, updatedUserData) => {
  return await adminClient.put(
    `http://localhost:8080/hoadon/update/${mahoadon}`,
    updatedUserData
  );
};
