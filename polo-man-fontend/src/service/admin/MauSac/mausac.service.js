import { adminClient } from "../http";

export const getAllMauSac = async () => {
  const res = await adminClient.get("http://localhost:8080/admin/mausac/getAll");
  return res;
};

const getOne = async (mamausac) => {
  const res = await adminClient.get(
    `http://localhost:8080/admin/mausac/${mamausac}`
  );
  return res;
};

const saveOrUpdateMauSac = async (form) => {
  const res = await adminClient.post(
    "http://localhost:8080/admin/mausac/add",
    form
  );
  return res;
};
// const deleteMauSac = async (mamausac) => {
//   const res = await adminClient.delete(
//     `http://localhost:8080/admin/mausac/${mamausac}`
//   );
//   return res;
// };
export {  saveOrUpdateMauSac, getOne};
