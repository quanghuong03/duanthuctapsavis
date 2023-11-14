import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { addressService, hoaDonService } from "../../../../service/admin";
import { toastService } from "../../../../service/common";
import { Button, Form, Input, Select, Spin } from "antd";
import { LoadingBox, LoadingPage } from "../../../common";

const UpdateOrder = () => {
  const { mahoadon } = useParams();
  const [order, setOrder] = useState();
  const navigate = useNavigate();
  const [loading, setLoading] = useState(true);

  const [form] = Form.useForm();

  useEffect(() => {
    (async () => {
      try {
        const orders = await hoaDonService.getOrder(mahoadon);
        const order = orders;
        if (!order) {
          navigate("/not-found");
        }
        // form.setFieldValue('note', order?.note || '')
        form.setFieldValue("tennguoinhan", order.data.tennguoinhan || "");
        form.setFieldValue("sodienthoai", order.data.sodienthoai || "");
        form.setFieldValue("diachi", order.data.diachi || "");
        form.setFieldValue("thanhpho", order.data.thanhpho || "");
        form.setFieldValue("quanhuyen", order.data.quanhuyen || "");
        form.setFieldValue("phuongxa", order.data.phuongxa || "");
        form.setFieldValue("ghichu", order.data.ghichu || "");
        setOrder(order);
        setLoading(false);
        console.log(order);
      } catch (error) {
        toastService.error(error.apiMessage);
      }
    })();
  }, []);

  const setAddressNote = (note) => {
    form.setFieldValue("street", note);
  };

  if (loading) {
    return (
      <div style={{ marginTop: "150px" }}>
        <LoadingBox />
      </div>
    );
  }

  const updateOrderHandle = async (form) => {
    try {
      await hoaDonService.changeProfileUserOrder(order.data.mahoadon, form);
      toastService.success("Update order successfully");
      navigate("/admin/orders");
    } catch (error) {
      toastService.error(error.apiMessage);
    }
  };

  return (
    <div>
      <div className="col-6">
        <div className="checkout-title">
          <h3>Update order</h3>
        </div>
        <Form layout="vertical" form={form} onFinish={updateOrderHandle}>
          <div>
            <div className="d-flex custom-user-form">
              <Form.Item
                label="Full name"
                name={"tennguoinhan"}
                rules={[{ required: true, message: "Full name is required" }]}
              >
                <Input placeholder="Full name" size="large" />
              </Form.Item>
              <Form.Item
                label="Phone number"
                name={"sodienthoai"}
                rules={[{ required: true, message: "phone is required" }]}
              >
                <Input placeholder="Phone number" size="large" type="number" />
              </Form.Item>
            </div>
          </div>

          <Form.Item
            label="Thành Phố"
            name={"thanhpho"}
            rules={[
              {
                required: true,
                message: "Thành phố không được để trống",
              },
            ]}
          >
            <Input placeholder="Thành phố" size="x-large" />
          </Form.Item>
          <Form.Item
            label="Quận Huyện"
            name={"quanhuyen"}
            rules={[
              {
                required: true,
                message: "Quận huyện không được để trống",
              },
            ]}
          >
            <Input placeholder="Quận huyện" size="x-large" />
          </Form.Item>
          <Form.Item
            label="Phường Xã"
            name={"phuongxa"}
            rules={[
              {
                required: true,
                message: "Phường xã không được để trống",
              },
            ]}
          >
            <Input placeholder="Phường Xã" size="x-large" />
          </Form.Item>
          <Form.Item
            label="Địa chị cụ thể"
            name={"diachi"}
            rules={[{ required: true, message: "detail is required" }]}
          >
            <Input size="large" placeholder="Số nhà 5," />
          </Form.Item>
          <Form.Item label="Note" name={"ghichu"}>
            <Input size="large" placeholder="Note" />
          </Form.Item>

          <Form.Item>
            <Button type="primary" htmlType="submit">
              Update
            </Button>
          </Form.Item>
        </Form>
      </div>
    </div>
  );
};

export { UpdateOrder };
