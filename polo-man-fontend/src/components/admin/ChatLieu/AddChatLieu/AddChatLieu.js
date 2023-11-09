import { Button, Form, Input } from "antd";
import { chatLieuSerivce } from "../../../../service/admin";
import { toastService } from "../../../../service/common";
const AddChatLieu = () => {
  const addChatLieulHandle = async (form) => {
    try {
      chatLieuSerivce.saveOrUpdateChatLieu(form);
      toastService.success("Thêm Chất liệu thành công");
    } catch (error) {
      console.log(error);
      toastService.error(error.apiMessage);
    }
  };

  return (
    <Form
      onFinish={addChatLieulHandle}
      labelCol={{ span: 4 }}
      wrapperCol={{ span: 8 }}
    >
      <Form.Item
        label="Tên"
        name="tenchatlieu"
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

export { AddChatLieu };
