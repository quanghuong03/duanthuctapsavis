import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { hoaDonService } from "../../../../service/admin";
import { LoadingBox } from "../../../common";
import { Button, Divider } from "antd";
import { Trang_Thai_Don_Hang_Map } from "../../../common/TrangThaiDonHang/TrangThaiDonHang";
import "./OrderDetail.css";

const OrderDetail = () => {
  const { mahoadon } = useParams();
  const [order, setOrder] = useState();
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    (async () => {
      const order = await hoaDonService.getOrder(mahoadon);
      setOrder(order.data);
      setLoading(false);
      console.log(order.data);
    })();
  }, []);

  if (loading) {
    return (
      <div
        style={{
          marginTop: "300px",
        }}
      >
        <LoadingBox />
      </div>
    );
  }

  return (
    <div className="order_detail">
      <div className="order_item">
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
            {order.diachi} - {order.phuongxa} - {order.quanhuyen} -{" "}
            {order.thanhpho}
            <Link to={`/admin/orders/update/${order.mahoadon}`}>
              <Button>
                <i className="fa-regular fa-pen-to-square"></i>
              </Button>
            </Link>
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
                      <img src={`/img/sanpham/${product.hinhanh}`} alt="" />
                    </div>
                  </div>
                  <div className="col-9">
                    <div className="order_product_title">
                      <h4>{product.tensanpham}</h4>
                      <h5>
                        Size : {product.sosize} - Màu: {product.tenmau}
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
          {/* {canComplete(order.status) && 
        <Popconfirm
            title="Cancel Order"
            description="Are you sure cancel this order!!"
            onConfirm={() =>  completeOrderHandle(order)}
            // onCancel={cancel}
            okText="YES"
            cancelText="NO"
        >
            <button className="btn btn-solid" disabled={!canComplete(order.status) || order.canceling}>
                Complete
            </button>
        </Popconfirm>}
        {canCancel(order.status) && 
        <Popconfirm
            title="Cancel Order"
            description="Are you sure cancel this order!!"
            onConfirm={() =>  cancelOrderHandle(order)}
            // onCancel={cancel}
            okText="YES"
            cancelText="NO"
        >
            <button className="btn btn-solid" disabled={!canCancel(order.status) || order.canceling}>
                Cancel
            </button>
        </Popconfirm>} */}
        </div>
      </div>
    </div>
  );
};

export { OrderDetail };
