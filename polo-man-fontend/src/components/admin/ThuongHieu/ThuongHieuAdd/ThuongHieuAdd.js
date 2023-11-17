import { Button, Form, Input } from "antd";
import { thuonghieuService } from "../../../../service/admin";
import { toastService } from "../../../../service/common";
const AddThuongHiec = () => {
  const addThuongHieulHandle = async (form) => {
    try {
      thuonghieuService.saveOrUpdateThuonghieu(form);
      toastService.success("Thêm Thương hiệu thành công");
    } catch (error) {
      console.log(error);
      toastService.error(error.apiMessage);
      }
      
  };

  return (
    <Form
      onFinish={addThuongHieulHandle}
      labelCol={{ span: 4 }}
      wrapperCol={{ span: 8 }}
    >
      <Form.Item
        label="Tên"
        name="tenthuonghieu"
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

export { AddThuongHiec };
