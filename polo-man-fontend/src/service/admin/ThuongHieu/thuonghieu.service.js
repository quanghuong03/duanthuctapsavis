import { adminClient } from "../http";

export const getThuongHieu = async () => {
  const res = await adminClient.get(
    "http://localhost:8080/admin/thuonghieu/getAll"
  );
  return res;
};
const getOne = async (mathuonghieu) => {
  const res = await adminClient.get(
    `http://localhost:8080/admin/thuonghieu/${mathuonghieu}`
  );
  return res;
};

const saveOrUpdateThuonghieu = async (form) => {
  const res = await adminClient.post(
    "http://localhost:8080/admin/thuonghieu/add",
    form
  );
  return res;
};
export { saveOrUpdateThuonghieu, getOne };
