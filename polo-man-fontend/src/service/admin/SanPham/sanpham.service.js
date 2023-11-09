import { adminClient } from "../http";

export const getAllSanPham = async (params) => {
  return await adminClient.get("http://localhost:8080/sanpham/getAll", {
    params,
  });
};

export const getProductById = async (masanpham) => {
  return await adminClient.get(`http://localhost:8080/sanpham/${masanpham}`);
};
