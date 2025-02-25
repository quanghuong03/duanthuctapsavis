import { Button, Form, Input } from "antd";
import { roleService } from "../../../../service/admin";
import { toastService } from "../../../../service/common";
import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { useNavigate } from "react-router-dom";
import XRegExp from "xregexp";
const UpdateRole = () => {
  const { id } = useParams();
  console.log(id);
  const navigate = useNavigate();
  const [role, setRole] = useState({});
  const [form] = Form.useForm();
  const validateInput = (rule, value, callback) => {
    const regex = XRegExp("^[\\p{L}0-9\\s^%/().,]+$");
    const maxLength = 200;

    if (value && value.length > maxLength) {
      callback(`Không vượt quá ${maxLength} kí tự`);
    } else if (value && !regex.test(value)) {
      callback("Không chứa ký tự đặc biệt");
    } else {
      callback();
    }
  };
  useEffect(() => {
    (async () => {
      const body = await roleService.getOne(id);
      setRole(body.data);
      console.log(body.data);
      form.setFieldsValue({
        ...body.data,
      });
    })();
  }, []);

  const updateHandle = async (form) => {
    try {
      const formData = {
        id: id,
        name: form.name,
     
      };
      roleService.createColor(formData);
      console.log(formData);
      toastService.success("Cập nhật chức vụ thành công");
      navigate("/admin/role");
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
        name="name"
        rules={[  { required: true, message: "Tên không được trống" },
        { validator: validateInput },]}
      >
        <Input />
      </Form.Item>
   

      <Form.Item wrapperCol={{ offset: 4 }}>
        <button type="primary" htmlType="submit">
          Cập nhật chức vụ
        </button>
      </Form.Item>
    </Form>
  );
};

export { UpdateRole };
