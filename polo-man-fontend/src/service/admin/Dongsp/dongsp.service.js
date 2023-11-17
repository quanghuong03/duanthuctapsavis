import { adminClient } from "../http";

export const getdongsp = async () => {
  const res = await adminClient.get(
    "http://localhost:8080/admin/dongsp/getAll"
  );
  return res;
};
const getOne = async (madongsp) => {
  const res = await adminClient.get(
    `http://localhost:8080/admin/dongsp/${madongsp}`
  );
  return res;
};

const saveOrUpdateDongsp = async (form) => {
  const res = await adminClient.post(
    "http://localhost:8080/admin/dongsp/add",
    form
  );
  return res;
};
export {  saveOrUpdateDongsp, getOne};

