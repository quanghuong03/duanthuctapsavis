import { adminClient } from "../http";

export const getOrders = async (params) => {
  return await adminClient.get("http://localhost:8080/hoadon", {
    params,
  });
};

export const changeStatusOrder = async (mahoadon, trangthai) => {
  return await adminClient.put(
    `http://localhost:8080/hoadon/status/${mahoadon}`,
    {
      trangthai: trangthai,
    }
  );
};
