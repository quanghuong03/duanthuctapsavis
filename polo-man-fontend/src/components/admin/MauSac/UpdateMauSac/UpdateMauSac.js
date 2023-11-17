import { Button, Form, Input } from "antd";
import { mauSacService } from "../../../../service/admin";
import { toastService } from "../../../../service/common";
import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { useNavigate } from "react-router-dom";
const UpdateMauSac = () => {
  const { mamausac } = useParams();
  console.log(mamausac);
  const navigate = useNavigate();
  const [mausac, setMauSac] = useState({});
  const [form] = Form.useForm();

  useEffect(() => {
    (async () => {
      const body = await mauSacService.getOne(mamausac);
      setMauSac(body.data);
      console.log(body.data);
      form.setFieldsValue({
        ...body.data,
      });
    })();
  }, []);

  const updateHandle = async (form) => {
    try {
      const formData = {
        mamausac: mamausac,
        tenmau: form.tenmau,
      };
      mauSacService.saveOrUpdateMauSac(formData);
      console.log(formData);
      toastService.success("Cập nhật mau sac thành công");
      navigate("/admin/mausac");
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
        name="tenmau"
        rules={[{ required: true, message: "Tên màu sắc không được trống" }]}
      >
        <Input />
      </Form.Item>

      <Form.Item wrapperCol={{ offset: 4 }}>
        <Button type="primary" htmlType="submit">
          Cập nhật mau sac
        </Button>
      </Form.Item>
    </Form>
  );
};

export { UpdateMauSac };
