import { useEffect, useState } from "react";
import { hoaDonKhachHang } from "../../../service/user";
import { toastService } from "../../../service/common";
import { Avatar, Divider, Empty, List, Popconfirm } from "antd";
import { Link } from "react-router-dom";
import { color } from "@cloudinary/url-gen/qualifiers/background";
import { Trang_Thai_Don_Hang_Map } from "../../common/TrangThaiDonHang/TrangThaiDonHang";
import "./UserOrderList.css";
import { EmptyPage, LoadingPage } from "../../common";
const UserOrderList = () => {
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);
  useEffect(() => {
    (async () => {
      try {
        const orderRes = await hoaDonKhachHang.getHoaDon();
        console.log(orderRes);
        const orders = orderRes?.data?.map((order) => {
          const totalPrice =
            order?.hoaDonChiTietResponses?.reduce((acrr, pre) => {
              return (acrr += pre.quantity * pre.price);
            }, 0) || 0;
          return {
            ...order,
            totalPrice,
          };
        });
        setOrders(orders);
        console.log(orders);
        setLoading(false);
      } catch (error) {
        toastService.error(error.apiMessage);
      }
    })();
  }, []);

  const canComplete = (statusCode) => {
    if (!statusCode) {
      return false;
    }
    return [1].includes(statusCode);
  };

  const canCancel = (statusCode) => {
    if (!statusCode) {
      return false;
    }
    return [1, 2].includes(statusCode);
  };

  const cancelOrderHandle = (order) => {
    if (order?.canceling || canCancel()) {
      return;
    }
    order.canceling = true;
    setOrders([...orders]);
    hoaDonKhachHang
      .cancelOrder(order.mahoadon, {
        ghichu: "",
      })
      .then(() => {
        order.status = "REQUEST_CANCEL";
        order.canceling = false;
        toastService.success("Update order successfully");
        setOrders([...orders]);
      })
      .catch((error) => {
        console.log(error);
        order.canceling = false;
        setOrders([...orders]);
        toastService.error(error.apiMessage);
      });
  };

  const completeOrderHandle = (order) => {
    if (order?.canceling || canComplete()) {
      return;
    }
    order.canceling = true;
    setOrders([...orders]);
    hoaDonKhachHang
      .changeStatusOrder(order.mahoadon, {
        trangthai: 3,
      })
      .then(() => {
        order.trangthai = 3;
        order.canceling = false;
        toastService.success("Update order successfully");
        setOrders([...orders]);
      })
      .catch((error) => {
        console.log(error);
        order.canceling = false;
        setOrders([...orders]);
        toastService.error(error.apiMessage);
      });
  };

  if (loading) {
    return <LoadingPage />;
  }

  if (!orders || orders?.length === 0) {
    return <EmptyPage />;
  }

  return (
    <div className="user_orders_page">
      <div className="breadcrumb-section">
        <div className="container">
          <h2>My Orders</h2>
        </div>
      </div>
      <div className="section-b-space">
        <div className="container">
          {orders.map((order, index) => {
            return (
              <div key={index} className="order_item">
                <div className="order_title">
                  <h3>
                    <i
                      style={{
                        color: "#d4b196",
                        marginRight: "1em",
                      }}
                      className="fa-solid fa-bag-shopping"
                    ></i>
                    {order.tennguoinhan} - {order.sodienthoai}
                  </h3>
                  <h4 className="mt-3" style={{ color: "#26aa99" }}>
                    <i
                      style={{
                        marginRight: "1em",
                      }}
                      className="fa-solid fa-truck-fast"
                    ></i>
                    {Trang_Thai_Don_Hang_Map[order.trangthai]}
                  </h4>
                  <h5 className="mt-3">
                    Địa chỉ : {order.diachi} - {order.phuongxa} -{" "}
                    {order.quanhuyen} - {order.thanhpho}{" "}
                  </h5>
                  <h5 className="mt-3">{order.ghichu}</h5>
                </div>

                <Divider />
                <div className="order_products">
                  {order.hoaDonChiTietResponses.map((product, index) => {
                    return (
                      <div className="order_product" key={index}>
                        <div className="row">
                          <div className="col-1">
                            <div className="order_product_image">
                              <img src={product.hinhanh} alt="" />
                            </div>
                          </div>
                          <div className="col-9">
                            <div className="order_product_title">
                              <h4>{product.tensanpham}</h4>
                              <h5>
                                Size: {product.sosize} - Màu: {product.tenmau}
                              </h5>
                            </div>
                            <div className="order_product_quantity">
                              <p>Quantity: {product.soluong}</p>
                            </div>
                            <div className="order_product_price">
                              <p>{product.soluong * product.dongia}</p>
                            </div>
                          </div>
                        </div>
                        <Divider />
                      </div>
                    );
                  })}
                </div>

                <div className="order_total_price">
                  <div className="d-flex justify-content-end">
                    Total Price: <span>{order.tonggia}</span>
                  </div>
                </div>
                <div className="actions d-flex justify-content-end">
                  {canComplete(order.trangthai) && (
                    <Popconfirm
                      title="Cancel Order"
                      description="Are you sure cancel this order!!"
                      onConfirm={() => completeOrderHandle(order)}
                      // onCancel={cancel}
                      okText="YES"
                      cancelText="NO"
                    >
                      <button
                        className="btn btn-primary"
                        disabled={
                          !canComplete(order.trangthai) || order.canceling
                        }
                      >
                        Complete
                      </button>
                    </Popconfirm>
                  )}

                  {canCancel(order.trangthai) && (
                    <Popconfirm
                      title="Cancel Order"
                      description="Are you sure cancel this order!!"
                      onConfirm={() => cancelOrderHandle(order)}
                      // onCancel={cancel}
                      okText="YES"
                      cancelText="NO"
                    >
                      <button
                        className="btn btn-primary"
                        disabled={
                          !canCancel(order.trangthai) || order.canceling
                        }
                      >
                        Cancel
                      </button>
                    </Popconfirm>
                  )}
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
};

export { UserOrderList };
