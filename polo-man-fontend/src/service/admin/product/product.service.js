import { adminClient } from "../http";

export const getAllProducts = async (params) => {
  return await adminClient.get("http://localhost:8080/admin/product/discount", {
    params,
  });
};

export const getAllProductsForAdmin = async (params) => {
  return await adminClient.get("http://localhost:8080/admin/product", {
    params,
  });
};

export const getAllProductsForUser = async (params) => {
  return await adminClient.get("http://localhost:8080/admin/product/homepage", {
    params,
  });
};

export const getProductById = async (productId) => {
  return await adminClient.get(
    `http://localhost:8080/admin/product/${productId}`
  );
};

export const getProductDetailById = async (productId) => {
  return await adminClient.get(
    `http://localhost:8080/admin/product/productDetail/${productId}`
  );
};

export const changeStatus = async (productId) => {
  return await adminClient.put(
    `http://localhost:8080/admin/product/changeStatus/${productId}`
  );
};

export const changeStautsDiscount = async (productId) => {
  return await adminClient.put(
    `http://localhost:8080/admin/product/changeStautsDiscount/${productId}`
  );
};

export const createProduct = async (createProductForm) => {
  return await adminClient.post(
    "http://localhost:8080/admin/product/add",
    createProductForm
  );
};

export const updateProductById = async (productId, updateProductForm) => {
  return await adminClient.put(
    `http://localhost:8080/admin/product/update/${productId}`,
    updateProductForm
  );
};
