import { adminClient } from "../http";

const getChatLieu = async () => {
  const res = await adminClient.get(
    "http://localhost:8080/admin/chatlieu/getAll"
  );
  return res;
};

const getOne = async (machatlieu) => {
  const res = await adminClient.get(
    `http://localhost:8080/admin/chatlieu/${machatlieu}`
  );
  return res;
};

const saveOrUpdateChatLieu = async (form) => {
  const res = await adminClient.post(
    "http://localhost:8080/admin/chatlieu/saveOrUpdate",
    form
  );
  return res;
};
export { getChatLieu, saveOrUpdateChatLieu, getOne };
