import { useEffect, useState } from "react";
import { hoaDonService } from "../../../../service/admin";
import {
  Button,
  Form,
  Input,
  Pagination,
  Popconfirm,
  Select,
  Tabs,
} from "antd";
import { toastService } from "../../../../service/common";
import { Trang_Thai_Don_Hang_Map } from "../../../common/TrangThaiDonHang/TrangThaiDonHang";
import { Trang_Thai_Don_Hang } from "../../../common/TrangThaiDonHang/TrangThaiDonHang";
import { SelectSearch } from "../../../common/SelectSearch";
import { LoadingBox } from "../../../common";
import { Link } from "react-router-dom";

const tabs = [
  {
    key: 1,
    label: `Chờ xác nhận`,
  },
  {
    key: 2,
    label: `Đã xác nhận`,
  },
  {
    key: 3,
    label: `Chuẩn bị hàng`,
  },
  {
    key: 4,
    label: `Đang giao hàng`,
  },
  {
    key: 5,
    label: `Hoàn thành`,
  },
  {
    key: 6,
    label: `Yêu cầu huỷ`,
  },
  {
    key: 7,
    label: `Đã Hủy`,
  },
];

const getUpdateAbleStatus = (statusCode) => {
  switch (statusCode) {
    case 1:
      return Trang_Thai_Don_Hang.filter((trangthai) =>
        [6, 4].includes(trangthai.value)
      );
    case 2:
      return Trang_Thai_Don_Hang.filter((trangthai) =>
        [4].includes(trangthai.value)
      );
    case 5:
      return Trang_Thai_Don_Hang.filter((trangthai) =>
        [4].includes(trangthai.value)
      );
    case 6:
      return Trang_Thai_Don_Hang.filter((trangthai) =>
        [4, 7].includes(trangthai.value)
      );
    case 7:
      return Trang_Thai_Don_Hang.filter((trangthai) =>
        [4, 2].includes(trangthai.value)
      );
    default:
      return [];
  }
};

const OrderList = () => {
  const [orders, setOrders] = useState([]);
  const [page, setPage] = useState(1);
  const [statusCode, setStatusCode] = useState(1);
  const [filterForm] = Form.useForm();
  const LIMIT = 10;
  const [loading, setLoading] = useState(true);

  const onPageChange = async (e) => {
    setPage(e);
  };

  useEffect(() => {
    (async () => {
      try {
        const orders = await getOrders({
          statuses: statusCode,
        });
        setOrders(orders);
      } catch (e) {
        toastService.error(e.apiMessage);
      }
    })();
  }, []);

  async function getOrders(form) {
    try {
      setLoading(true);
      const { data } = await hoaDonService.getOrders(form);
      setLoading(false);
      return data;
    } catch (error) {
      toastService.error(error.apiMessage);
      return [];
    }
  }

  const filterOrder = async (form) => {
    const orders = await getOrders(form);
    setOrders(orders);
    setPage(1);
  };

  async function onSearchHandle(form) {
    filterOrder({
      ...form,
      trangthai: statusCode,
    });
  }

  const toggleShowUpdateOrderForm = (order) => {
    const showUpdateStatusForm = !order.showUpdateStatusForm;
    order.showUpdateStatusForm = showUpdateStatusForm;
    setOrders([...orders]);
  };

  const updateOrderStatusHandle = (order, trangthai) => {
    order.isUpdating = true;
    setOrders([...orders]);
    hoaDonService
      .changeStatusOrder(order.mahoadon, trangthai)
      .then(() => {
        order.trangthai = trangthai;
        order.showUpdateStatusForm = false;
        order.isUpdating = false;
        setOrders([...orders]);
        toastService.success("Cập nhật thành công");
      })
      .catch((err) => {
        toastService.error(err.apiMessage);
        order.showUpdateStatusForm = false;
        order.isUpdating = false;
        setOrders([...orders]);
      });
  };

  const orderStatusTabChangeHandle = (statusCode) => {
    setStatusCode(statusCode);
    const formValue = filterForm.getFieldsValue();

    filterOrder({
      ...formValue,
      trangthai: statusCode,
    });
  };

  return (
    <div>
      <Tabs
        defaultActiveKey="1"
        items={tabs}
        onChange={orderStatusTabChangeHandle}
        activeKey={statusCode}
      />
      <Form
        layout="inline"
        className="my-3"
        onFinish={onSearchHandle}
        form={filterForm}
      >
        <Form.Item name={"sodienthoai"}>
          <Input placeholder={"Số điện thoại"} />
        </Form.Item>
        <Form.Item name={"ghichu"}>
          <Input placeholder={"Ghi chú"} />
        </Form.Item>
        <Form.Item>
          <Button htmlType="submit" type="primary">
            Search
          </Button>
        </Form.Item>
      </Form>

      <table className="mt-3 table table-bordered">
        <thead>
          <tr>
            <th>Mã hóa đơn</th>
            <th>Khách hàng</th>
            <th>Địa chỉ</th>
            <th>Số điện thoại</th>
            <th>Tổng tiền</th>
            <th>Ghi chú</th>
            <th>Trạng thái</th>
            <th>Thao tác</th>
          </tr>
        </thead>
        <tbody>
          {orders
            .slice((page - 1) * LIMIT, (page - 1) * LIMIT + LIMIT)
            .map((order) => {
              return (
                <tr key={order.mahoadon}>
                  <td>
                    <Link to={`/admin/orders/${order.mahoadon}`}>
                      {order.mahoadon}
                    </Link>
                  </td>
                  <td>{order.tennguoinhan}</td>
                  <td>{order.diachi}</td>
                  <td>{order.sodienthoai}</td>
                  <td>{order.tonggia}</td>
                  <td>{order.ghichu}</td>
                  <td>
                    {!order.showUpdateStatusForm && (
                      <div>
                        {Trang_Thai_Don_Hang_Map[order.trangthai]}
                        {/* {getUpdateAbleStatus(order.status)?.length !== 0 &&
                                    <button className="btn" onClick={() => toggleShowUpdateOrderForm(order)} >
                                        <i className="fa-regular fa-pen-to-square"></i>
                                    </button>} */}
                      </div>
                    )}
                  </td>
                  <td
                    style={{
                      width: "auto",
                      minWidth: "250px",
                    }}
                  >
                    <div className="actions">
                      <div className="d-flex">
                        <Form.Item
                          style={{ margin: 0 }}
                          initialValue={order.trangthai}
                        >
                          {getUpdateAbleStatus(order.trangthai).map(
                            (option) => {
                              console.log(order.trangthai);
                              return (
                                <Popconfirm
                                  title="Cập nhật"
                                  description="Bạn có chắc muốn xác nhận ?"
                                  onConfirm={() =>
                                    updateOrderStatusHandle(order, option.value)
                                  }
                                  // onCancel={cancel}
                                  okText="Xác nhận"
                                  cancelText="Huỷ"
                                >
                                  <Button
                                    key={option.value}
                                    type={
                                      option.value === "CANCELED"
                                        ? "danger"
                                        : "primary"
                                    }
                                    disabled={order.isUpdating}
                                  >
                                    {option.label}
                                  </Button>
                                </Popconfirm>
                              );
                            }
                          )}
                        </Form.Item>
                      </div>
                    </div>
                  </td>
                </tr>
              );
            })}
        </tbody>
      </table>

      <Pagination
        defaultCurrent={1}
        total={orders.length}
        pageSize={LIMIT}
        onChange={onPageChange}
      />
    </div>
  );
};

export { OrderList };
