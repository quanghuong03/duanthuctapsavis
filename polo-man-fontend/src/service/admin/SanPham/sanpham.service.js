import { adminClient } from "../http";

export const getAllSanPham = async (params) => {
  return await adminClient.get("http://localhost:8080/sanpham/findAll", {
    params,
  });
};

export const getProductById = async (masanpham) => {
  return await adminClient.get(`http://localhost:8080/sanpham/${masanpham}`);
};

export const createProduct = async (createProductForm) => {
  return await adminClient.post(
    "http://localhost:8080/sanpham/add",
    createProductForm
  );
};

export const updateProductById = async (masanpham, updateProductForm) => {
  return await adminClient.put(
    `http://localhost:8080/sanpham/update/${masanpham}`,
    updateProductForm
  );
};
