import { Button, Form, Input } from "antd";
import { mauSacService } from "../../../../service/admin";
import { toastService } from "../../../../service/common";
const AddMauSac = () => {
  const addMauSaclHandle = async (form) => {
    try {
      mauSacService.saveOrUpdateMauSac(form);
      toastService.success("Thêm màu sắc thành công");
    } catch (error) {
      console.log(error);
      toastService.error(error.apiMessage);
      }
      
  };

  return (
    <Form
      onFinish={addMauSaclHandle}
      labelCol={{ span: 4 }}
      wrapperCol={{ span: 8 }}
    >
      <Form.Item
        label="Tên"
        name="tenmau"
        rules={[{ required: true, message: "Tên không được trống" }]}
      >
        <Input />
      </Form.Item>
      <Form.Item wrapperCol={{ offset: 4 }}>
        <Button type="primary" htmlType="submit">
          Thêm Material
        </Button>
      </Form.Item>
    </Form>
  );
};

export { AddMauSac };
