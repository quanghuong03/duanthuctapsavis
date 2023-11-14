import { adminClient } from "../http";

export const getdongsp = async () => {
  const res = await adminClient.get(
    "http://localhost:8080/admin/dongsp/getAll"
  );
  return res;
};
