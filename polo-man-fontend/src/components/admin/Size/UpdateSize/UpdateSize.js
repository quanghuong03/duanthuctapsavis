import { Button, Form, Input } from "antd";
import { sizeService } from "../../../../service/admin";
import { toastService } from "../../../../service/common";
import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { useNavigate } from "react-router-dom";
const UpdateSize = () => {
  const { masize } = useParams();
  console.log(masize);
  const navigate = useNavigate();
  const [size, setSize] = useState({});
  const [form] = Form.useForm();

  useEffect(() => {
    (async () => {
      const body = await sizeService.getOne(masize);
      setSize(body.data);
      console.log(body.data);
      form.setFieldsValue({
        ...body.data,
      });
    })();
  }, []);

  const updateHandle = async (form) => {
    try {
      const formData = {
        masize: masize,
          sosize: form.sosize,
          chieucao: form.chieucao,
        cannang: form.cannang
      };
     sizeService.saveOrUpdateSize(formData);
      console.log(formData);
      toastService.success("Cập nhật size thành công");
      navigate("/admin/size");
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
        name="sosize"
        rules={[{ required: true, message: "Số size không được trống" }]}
      >
        <Input />
          </Form.Item>
          <Form.Item
        label="Name"
        name="chieucao"
        rules={[{ required: true, message: "Chiều cao không được trống" }]}
      >
        <Input />
          </Form.Item>
          <Form.Item
        label="Name"
        name="cannang"
        rules={[{ required: true, message: "Cân nặng không được trống" }]}
      >
        <Input />
      </Form.Item>

      <Form.Item wrapperCol={{ offset: 4 }}>
        <Button type="primary" htmlType="submit">
          Cập nhật size
        </Button>
      </Form.Item>
    </Form>
  );
};

export { UpdateSize };
