import { Button, Form, Input } from "antd";
import { chatLieuSerivce } from "../../../../service/admin";
import { toastService } from "../../../../service/common";
import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { useNavigate } from "react-router-dom";
const UpdateChatLieu = () => {
  const { machatlieu } = useParams();
  console.log(machatlieu);
  const navigate = useNavigate();
  const [chatlieu, setChatLieu] = useState({});
  const [form] = Form.useForm();

  useEffect(() => {
    (async () => {
      const body = await chatLieuSerivce.getOne(machatlieu);
      setChatLieu(body.data);
      console.log(body.data);
      form.setFieldsValue({
        ...body.data,
      });
    })();
  }, []);

  const updateHandle = async (form) => {
    try {
      const formData = {
        machatlieu: machatlieu,
        tenchatlieu: form.tenchatlieu,
      };
      chatLieuSerivce.saveOrUpdateChatLieu(formData);
      console.log(formData);
      toastService.success("Cập nhật Chất liệu thành công");
      navigate("/admin/chatlieu");
    } catch (error) {
      console.log(error);
      toastService.error(error.apiMessage);
    }
  };

  return (
    <Form
      form={form}
      onFinish={updateHandle}
      labelCol={{ span: 4 }}
      wrapperCol={{ span: 8 }}
    >
      <Form.Item
        label="Name"
        name="tenchatlieu"
        rules={[{ required: true, message: "Tên Chat lieu không được trống" }]}
      >
        <Input />
      </Form.Item>

      <Form.Item wrapperCol={{ offset: 4 }}>
        <Button type="primary" htmlType="submit">
          Cập nhật Chất liệu
        </Button>
      </Form.Item>
    </Form>
  );
};

export { UpdateChatLieu };
