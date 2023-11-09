import { Checkbox, Form, Input, Select } from "antd";
import "./Checkout.css";
import { useEffect, useState } from "react";
import {
  giohangService,
  userAuthService,
  hoaDonKhachHang,
} from "../../../service/user";
import { toastService } from "../../../service/common";
import { addressService } from "../../../service/admin";
import { selectSearchDataUtil } from "../../../utils";
import { SelectSearch } from "../../common/SelectSearch";
import { useNavigate } from "react-router-dom";
import { LoadingPage } from "../../common/LoadingPage";

const Checkout = () => {
  const [checkOutProducts, setCheckOutProducts] = useState([]);

  const [provinceOptions, setProvinceOptions] = useState([]);
  const [districtOptions, setDistrictOptions] = useState([]);
  const [wardOptions, setWardOptions] = useState([]);

  const [form] = Form.useForm();

  const [province, setProvince] = useState();
  const [district, setDistrict] = useState();
  const [ward, setWard] = useState();

  const [useUserProfile, setUseUserProfile] = useState(true);
  const [shipPrice, setShipPrice] = useState(0);

  const [loading, setLoading] = useState(true);

  const navigate = useNavigate();

  useEffect(() => {
    (async () => {
      try {
        const res = await giohangService.getProductsByTrangThai();

        setCheckOutProducts(res.data.list);
        console.log(res);

        const userInfo = userAuthService.getAuthInfo();
        form.setFieldValue("tennguoinhan", userInfo?.tenkhachhang || "");
        form.setFieldValue("sodienthoai", userInfo?.sodienthoai || "");
        // const addressRes = await userAddressService.getAddresses();
        // const address = addressRes.data;
        // // if (address) {
        //   const { province, district, ward, street } = address;
        //   selectProvince(province);
        //   await loadDistrictOptions(province);
        //   selectDistrict(district);
        //   await loadWardOptions(district);
        //   selectWard(ward);
        //   setAddressNote(street || "");
        //   await calculateShipPrice({ province, district });
        // }

        setLoading(false);
      } catch (error) {
        toastService.error(error.apiMessage);
      }
    })();
  }, []);

  const addOrderSubmitHandle = async () => {
    const userInfo = userAuthService.getAuthInfo();
    try {
      await form.validateFields();
    } catch (error) {
      return;
    }
    try {
      const formValue = form.getFieldsValue();
      const hoadonchitiet = checkOutProducts.map((cp) => {
        return {
          dongia: cp.giaban,
          machitietsanpham: cp.mactsp,
          soluong: cp.soluong,
        };
      });
      const request = {
        ...formValue,
        hoadonchitiet,
        tonggia: getTotalPrice(),
      };
      console.log(userInfo?.makhachhang);
      const addOrderRes = await hoaDonKhachHang.addOrder(
        userInfo.makhachhang,
        request
      );
      toastService.success("Checkout Successfully");
    } catch (error) {
      toastService.error(error.apiMessage);
    }
  };
  const getSubTotalPrice = () => {
    if (!checkOutProducts) {
      return 0;
    }
    return checkOutProducts.reduce((acrr, pre) => {
      return (acrr += pre.soluong * pre.giaban);
    }, 0);
  };

  const getTotalPrice = () => {
    return getSubTotalPrice() + shipPrice;
  };

  if (loading) {
    return <LoadingPage />;
  }

  return (
    <div className="checkout-page">
      <div className="breadcrumb-section">
        <div className="container">
          <h2>CHECK OUT</h2>
        </div>
      </div>
      <div className="section-b-space">
        <div className="container">
          <div className="row">
            <div className="col-6">
              <div className="checkout-title">
                <h3>Billing Details</h3>
              </div>
              <Form layout="vertical" form={form}>
                <div>
                  <div className="d-flex custom-user-form">
                    <Form.Item
                      label="Full name"
                      name={"tennguoinhan"}
                      rules={[
                        { required: true, message: "Full name is required" },
                      ]}
                    >
                      <Input placeholder="Full name" size="large" />
                    </Form.Item>
                    <Form.Item
                      label="Phone number"
                      name={"sodienthoai"}
                      rules={[{ required: true, message: "phone is required" }]}
                    >
                      <Input
                        placeholder="Phone number"
                        size="large"
                        type="number"
                      />
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
                <Form.Item label="Note" name={"note"}>
                  <Input size="large" placeholder="Note" />
                </Form.Item>
              </Form>
            </div>
            <div className="col-6">
              <div className="checkout-details">
                <div className="order-box">
                  <div className="title-box">
                    <div>
                      Product <span>Total</span>
                    </div>
                  </div>

                  <ul className="qty">
                    {checkOutProducts?.map((p, index) => {
                      return (
                        <li key={index}>
                          {p.tensanpham} × {p.soluong}{" "}
                          <span>{p.giaban * p.soluong}</span>
                        </li>
                      );
                    })}
                  </ul>

                  <ul className="sub-total">
                    <li>
                      Subtotal{" "}
                      <span className="count">{getSubTotalPrice()}</span>
                    </li>
                    <li>
                      Shipping
                      <span className="count">{shipPrice}</span>
                    </li>
                  </ul>

                  <ul className="total">
                    <li>
                      Total <span className="count">{getTotalPrice()}</span>
                    </li>
                  </ul>
                </div>

                <div className="payment-box">
                  <div className="text-end">
                    <button
                      onClick={addOrderSubmitHandle}
                      className="btn-solid btn"
                    >
                      Place Order
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Checkout;
