import { Form, Input } from "antd";
import { Link, useSearchParams } from "react-router-dom";
import { userAuthService } from "../../../service/user";
import { toastService } from "../../../service/common";
import { useNavigateOrRedirectUrl } from "../../../hook";
import { useEffect } from "react";

const UserLoginPage = () => {
  const [setPage] = useNavigateOrRedirectUrl();

  useEffect(() => {
    if (userAuthService.isLogin()) {
      setPage("/");
    }
  });

  const loginHandle = async (form) => {
    try {
      const res = await userAuthService.login(form);
      userAuthService.saveAuthInfo(res.data);
      toastService.success("Đăng nhập thành công");
      setPage("/");
    } catch (error) {
      toastService.error(error.apiMessage);
    }
  };

  return (
    <div className="login-page">
      <div className="breadcrumb-section">
        <div className="container">
          <h2>Khách hàng LOGIN</h2>
        </div>
      </div>
      <div className="container login-form mt-3">
        <div className="page-info"></div>
        <div className="row">
          <div className="col-6">
            <h3>LOGIN</h3>
            <div className="theme-card">
              <Form
                className="theme-form"
                layout="vertical"
                onFinish={loginHandle}
              >
                <Form.Item
                  name={"email"}
                  label="Email"
                  rules={[{ required: true, message: "Email is required" }]}
                >
                  <Input placeholder="email/username" size="large" />
                </Form.Item>
                <Form.Item
                  name={"matkhau"}
                  label="Mật khẩu"
                  rules={[{ required: true, message: "Password is required" }]}
                >
                  <Input placeholder="matkhau" type="matkhau" size="large" />
                </Form.Item>
                <button type="submit" className="btn btn-solid">
                  Login
                </button>
              </Form>
            </div>
          </div>
          <div className="col-6">
            <h3>New Customer</h3>
            <div className="theme-card authentication-right">
              <h6 className="title-font">Create A Account</h6>
              <p>
                Sign up for a free account at our store. Registration is quick
                and easy. It allows you to be able to order from our shop. To
                start shopping click register.
              </p>
              <Link to={"/register"}>
                <button className="btn btn-solid">Create an Account</button>
              </Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export { UserLoginPage };
