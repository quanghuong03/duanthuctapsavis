import { useEffect, useState } from "react";
import { userAddressService, userAuthService } from "../../../service/user";
// import { addressService } from "../../../services/admin";
import "./UserAccount.css";
import { Button, Divider } from "antd";
import { Link } from "react-router-dom";

const UserAccount = () => {
  const [userInfo, setUserInfo] = useState(userAuthService.getAuthInfo());
  const [address, setAddress] = useState();

  //   useEffect(() => {
  //     (async () => {
  //       const addressRes = await userAddressService.getAddresses();
  //       const address = addressRes.data;

  //       setAddress(address);
  //     })();
  //   }, []);

  return (
    <div>
      <div className="breadcrumb-section">
        <div className="container">
          <h2>My Account</h2>
        </div>
      </div>
      <div className="container" style={{ padding: "70px 0" }}>
        <div className="user_info_menu">
          <h3>Thông tin của bạn</h3>
          <ul className="d-flex mt-3">
            <li className="user_info_item">
              <h6>Họ Và Tên: {userInfo.tenkhachhang}</h6>
            </li>
            <li className="user_info_item">
              <h6>Số Điện Thoại: {userInfo.sodienthoai}</h6>
            </li>
            <li className="user_info_item">
              <h6>Email: {userInfo.email}</h6>
            </li>
          </ul>
        </div>
        <Divider />

        <div className="user_info_menu">
          <h3>Address</h3>
          <ul className="d-flex mt-3">
            <li className="user_info_item">
              <h6>
                {!address && (
                  <div>
                    {/* <p>You have'nt address infonation</p>
                    <Link to={"/account/address/edit"}>Add address info</Link> */}
                    <p> Địa chỉ : {userInfo.diachi}</p>
                  </div>
                )}
                {address && (
                  <div>
                    <p>
                      {/* {`${address.street || ""} - `} {address.ward_name} -{" "}
                      {address.district_name} - {address.province_name} */}
                      <Link to={"/account/address/edit"}>
                        <i className="ml-3 fa-regular fa-pen-to-square"></i>
                      </Link>
                    </p>
                  </div>
                )}
              </h6>
            </li>
          </ul>
        </div>
      </div>
    </div>
  );
};

export { UserAccount };
