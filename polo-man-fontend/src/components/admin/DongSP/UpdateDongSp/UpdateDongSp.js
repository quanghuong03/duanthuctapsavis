import { Button, Form, Input } from "antd";
import { DongspService } from "../../../../service/admin";
import { toastService } from "../../../../service/common";
import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { useNavigate } from "react-router-dom";
const UpdateDongSp = () => {
  const { madongsp } = useParams();
  console.log(madongsp);
  const navigate = useNavigate();
  const [dongsp, setDongSp] = useState({});
  const [form] = Form.useForm();

  useEffect(() => {
    (async () => {
      const body = await DongspService.getOne(madongsp);
      setDongSp(body.data);
      console.log(body.data);
      form.setFieldsValue({
        ...body.data,
      });
    })();
  }, []);

  const updateHandle = async (form) => {
    try {
      const formData = {
        madongsp: madongsp,
        tendongsp: form.tendongsp,
      };
      DongspService.saveOrUpdateDongsp(formData);
      console.log(formData);
      toastService.success("Cập nhật dòng sản phẩm thành công");
      navigate("/admin/dongsp");
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
        name="tendongsp"
        rules={[{ required: true, message: "Tên dòng sản phẩm không được trống" }]}
      >
        <Input />
      </Form.Item>

      <Form.Item wrapperCol={{ offset: 4 }}>
        <Button type="primary" htmlType="submit">
          Cập nhật dòng sản phẩm 
        </Button>
      </Form.Item>
    </Form>
  );
};

export { UpdateDongSp };
