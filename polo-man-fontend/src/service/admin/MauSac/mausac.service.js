import { adminClient } from "../http";

export const getAllMauSac = async () => {
  const res = await adminClient.get("http://localhost:8080/mausac/getAll");
  return res;
};
