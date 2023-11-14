import { adminClient } from "../http";

export const getAllSize = async () => {
  const res = await adminClient.get("http://localhost:8080/size/getAll");
  return res;
};
