import { Button, Form, Input } from "antd";
import { thuonghieuService } from "../../../../service/admin";
import { toastService } from "../../../../service/common";
import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { useNavigate } from "react-router-dom";
const UpdateThuongHieu = () => {
  const { mathuonghieu } = useParams();
  console.log(mathuonghieu);
  const navigate = useNavigate();
  const [thuonghieu, setThuonghieu] = useState({});
  const [form] = Form.useForm();

  useEffect(() => {
    (async () => {
      const body = await thuonghieuService.getOne(mathuonghieu);
      setThuonghieu(body.data);
      console.log(body.data);
      form.setFieldsValue({
        ...body.data,
      });
    })();
  }, []);

  const updateHandle = async (form) => {
    try {
      const formData = {
        mathuonghieu: mathuonghieu,
        tenthuonghieu: form.tenthuonghieu,
      };
      thuonghieuService.saveOrUpdateThuonghieu(formData);
      console.log(formData);
      toastService.success("Cập nhật thương hiệu thành công");
      navigate("/admin/thuonghieu");
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
        name="tenthuonghieu"
        rules={[{ required: true, message: "Tên thương hiệu không được trống" }]}
      >
        <Input />
      </Form.Item>

      <Form.Item wrapperCol={{ offset: 4 }}>
        <Button type="primary" htmlType="submit">
          Cập nhật thương hiệu
        </Button>
      </Form.Item>
    </Form>
  );
};

export { UpdateThuongHieu };
