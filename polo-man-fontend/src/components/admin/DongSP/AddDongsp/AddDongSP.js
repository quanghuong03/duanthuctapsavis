import { Button, Form, Input } from "antd";
import { DongspService } from "../../../../service/admin";
import { toastService } from "../../../../service/common";
const AddDongSp = () => {
  const addDongSplHandle = async (form) => {
    try {
      DongspService.saveOrUpdateDongsp(form);
      toastService.success("Thêm dòng sản phẩm thành công");
    } catch (error) {
      console.log(error);
      toastService.error(error.apiMessage);
      }
      
  };

  return (
    <Form
      onFinish={addDongSplHandle}
      labelCol={{ span: 4 }}
      wrapperCol={{ span: 8 }}
    >
      <Form.Item
        label="Tên"
        name="tendongsp"
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

export { AddDongSp };
