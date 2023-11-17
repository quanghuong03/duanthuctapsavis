import { Button, Form, Input } from "antd";
import { sizeService } from "../../../../service/admin";
import { toastService } from "../../../../service/common";
const AddSize = () => {
  const addSizelHandle = async (form) => {
    try {
      sizeService.saveOrUpdateSize(form);
      toastService.success("Thêm size thành công");
    } catch (error) {
      console.log(error);
      toastService.error(error.apiMessage);
      }
      
  };

  return (
    <Form
      onFinish={addSizelHandle}
      labelCol={{ span: 4 }}
      wrapperCol={{ span: 8 }}
    >
         <Form.Item
        label="Số size"
        name="sosize"
        rules={[{ required: true, message: "Số size không được trống" }]}
      >
        <Input />
          </Form.Item>
          <Form.Item
        label="Chiều cao"
        name="chieucao"
        rules={[{ required: true, message: "Chiều cao không được trống" }]}
      >
        <Input />
          </Form.Item>
          <Form.Item
        label="Cân nặng"
        name="cannang"
        rules={[{ required: true, message: "Cân nặng không được trống" }]}
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

export { AddSize };
