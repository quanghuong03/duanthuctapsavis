import { adminClient } from "../http";

export const getThuongHieu = async () => {
  const res = await adminClient.get(
    "http://localhost:8080/admin/thuonghieu/getAll"
  );
  return res;
};
