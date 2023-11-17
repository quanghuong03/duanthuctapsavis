import { adminClient } from "../http";

export const getAllSize = async () => {
  const res = await adminClient.get("http://localhost:8080/admin/size/getAll");
  return res;
};
const getOne = async (masize) => {
  const res = await adminClient.get(
    `http://localhost:8080/admin/size/${masize}`
  );
  return res;
};

const saveOrUpdateSize = async (form) => {
  const res = await adminClient.post(
    "http://localhost:8080/admin/size/add",
    form
  );
  return res;
};
export {  saveOrUpdateSize, getOne};

