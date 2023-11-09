import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { toastService } from "../../../../service/common";
import { sanphamService } from "../../../../service/admin";
import { LoadingPage } from "../../../common";
import "./UserProductDetail.css";
import { Carousel, Divider, Form, Input } from "antd";
import { giohangService, userAuthService } from "../../../../service/user";
import { useNavigateLoginPage } from "../../../../hook";

const UserProductDetail = () => {
  const { masanpham } = useParams();
  const [product, setProduct] = useState();
  const [loading, setLoading] = useState(true);
  const [images, setImages] = useState([]);
  const [sizes, setSizes] = useState([]);
  const [colors, setColors] = useState([]);
  const [productDetailMap, setProductDetailMap] = useState([]);

  const [productDetailId, setProductDetailId] = useState("");

  const [sizePicker, setSize] = useState("");
  const [colorPicker, setColor] = useState("");

  const [quantityForm] = Form.useForm();

  const [navigateLogin] = useNavigateLoginPage();

  useEffect(() => {
    (async () => {
      try {
        const productRes = await sanphamService.getProductById(masanpham);
        const product = productRes.data;
        setProduct(productRes.data);

        const productDetails = product.list || [];

        const sizeMap = productDetails.reduce((acrr, pre, index) => {
          return {
            ...acrr,
            [pre.masize]: {
              masize: pre.masize,
              sosize: pre.sosize,
              index,
            },
          };
        }, {});

        setSizes(Object.values(sizeMap));

        const colors = productDetails.reduce((acrr, pre, index) => {
          return {
            ...acrr,
            [pre.mamausac]: {
              mamausac: pre.mamausac,
              tenmau: pre.tenmau,
              index,
            },
          };
        }, {});
        setColors(Object.values(colors));

        const productDetailMap = productDetails.reduce((acrr, pre) => {
          const colorSizeKey = `${pre.masize}_${pre.mamausac}`;
          return {
            ...acrr,
            [colorSizeKey]: pre,
          };
        }, {});

        setProductDetailMap(productDetailMap);
        setLoading(false);
      } catch (error) {
        toastService.error(error.apiMessage);
      }
    })();
  }, []);

  const sizeChangeHandle = (index) => {
    setSize(index);
  };

  const getCurrentProductDetailKey = () => {
    return `${sizePicker?.masize || ""}_${colorPicker?.mamausac || ""}`;
  };

  const getAvailableQuanity = () => {
    const colorSizeKey = getCurrentProductDetailKey();
    const productDetail = productDetailMap[colorSizeKey];
    return productDetail?.soluongton || 0;
  };

  const addProductToCardHandle = async () => {
    if (!userAuthService.isLogin()) {
      toastService.info(
        <a>
          You need to login to perform this action,
          <div>
            <a style={{ fontWeight: 700, color: "#007bff" }}>Login now</a>
          </div>
        </a>,
        {
          onClick: () => navigateLogin(),
        }
      );
      return;
    }

    const quantity = quantityForm.getFieldsValue()?.quantity;
    const productDetail = getSelectedProductDetail();

    if (!productDetail) {
      toastService.info("Vui lòng chọn sản phẩm");
      return;
    }

    if (!quantity) {
      toastService.info("Vui lòng nhập số lượng");
      return;
    }

    if (quantity > productDetail.soluongton) {
      toastService.info("Số lượng sản phẩm không đủ");
      return;
    }

    const req = {
      mactsp: productDetail.mactsp,
      soluong: quantity,
    };

    try {
      const authInfo = await userAuthService.getAuthInfo();
      const res = await giohangService.addProduct(authInfo.makhachhang, req);
      toastService.success("Product added to cart successfully");
      console.log(res);
    } catch (error) {
      toastService.error(error.apiMessage);
    }
  };

  const getSelectedProductDetail = () => {
    return productDetailMap[getCurrentProductDetailKey()] || null;
  };

  const canAddToCard = () => {
    const quantity = quantityForm.getFieldsValue()?.quantity;
    return quantity && quantity <= getAvailableQuanity();
  };
  if (loading) {
    return <LoadingPage />;
  }
  return (
    <div>
      <div className="breadcrumb-section">
        <div className="container"></div>
      </div>
      <div className={"container product_detail"}>
        <div className={"row"}>
          <div className={"col-6 "}>
            <div className={"main_image"}>
              <Carousel autoplay>
                <div className={"main_image"}>
                  <img
                    className={"product-image"}
                    src={`/img/sanpham/${product.hinhanh}`}
                    alt="Image 1"
                  />
                </div>
                <div>
                  <img
                    className={"product-image"}
                    src={`/img/sanpham/${product.hinhanh}`}
                    alt="Image 2"
                  />
                </div>
                <div>
                  <img
                    className={"product-image"}
                    src={`/img/sanpham/${product.hinhanh}`}
                    alt="Image 3"
                  />
                </div>
                {/* Add more images here */}
              </Carousel>
            </div>
          </div>
          <div className={"col-6"}>
            <div className={"product-right "}>
              <div className={"product-count"}>
                <ul>
                  <li>
                    /<span className="p-counter">37</span>
                    <span className="lang">Orders in last 24 hours</span>
                  </li>
                  <li>
                    <img
                      src="http://themes.pixelstrap.com/multikart/assets/images/person.gif"
                      className="img-fluid user_img"
                      alt="image"
                    />
                    <span className="p-counter">44</span>
                    <span className="lang">active view this</span>
                  </li>
                </ul>
              </div>
              <h2>{product.tensanpham}</h2>
              <div className="rating-section">
                <div className="rating">
                  <i className="fa fa-star"></i> <i className="fa fa-star"></i>{" "}
                  <i className="fa fa-star"></i> <i className="fa fa-star"></i>{" "}
                  <i className="fa fa-star"></i>
                </div>
                <h6>120 ratings</h6>
              </div>

              <div className="label-section">
                <span className="badge badge-grey-color">#1 Best seller</span>
                <span className="label-text">in fashion</span>
              </div>

              <h3 className="price-detail">
                {product.giaban}
                <del>{product.giaban}</del>
                <span>10%</span>
              </h3>

              <ul className="color-variant">
                {colors.map((color, index) => {
                  return (
                    <li
                      onClick={() => setColor(color)}
                      className={
                        color.index === colorPicker?.index ? "active" : ""
                      }
                      style={{
                        border: "1px solid lightgray",
                        display: "flex",
                        justifyContent: "center",
                        alignItems: "center",
                        borderRadius: "0.25rem",
                        width: "50px",
                      }}
                      key={index}
                    >
                      {color.tenmau}
                    </li>
                  );
                })}
              </ul>

              <div
                id="selectSize"
                className="addeffect-section product-description border-product"
              >
                <h6 className="product-title size-text">
                  select size{" "}
                  <span>
                    <a
                      href=""
                      data-bs-toggle="modal"
                      data-bs-target="#sizemodal"
                    >
                      size chart
                    </a>
                  </span>
                </h6>
                <h6 className="error-message">please select size</h6>
                <div className="size-box">
                  <ul>
                    {sizes.map((size, index) => {
                      return (
                        <li
                          key={index}
                          className={
                            size.index === sizePicker?.index ? "active" : ""
                          }
                          onClick={() => sizeChangeHandle(size)}
                        >
                          {size.sosize}
                        </li>
                      );
                    })}
                  </ul>
                </div>
              </div>

              <h6 className="product-title">Available</h6>

              <div className="qty-box mt-3">{getAvailableQuanity()}</div>

              <h6 className="product-title">Quantity</h6>
              <div className="qty-box mt-3">
                <Form form={quantityForm}>
                  <Form.Item name={"quantity"} initialValue={1}>
                    <Input
                      min={1}
                      style={{ width: "100px" }}
                      type={"number"}
                      placeholder={"Số lượng"}
                    />
                  </Form.Item>
                </Form>
              </div>

              <div className="product-buttons">
                <button
                  id="cartEffect"
                  onClick={addProductToCardHandle}
                  abled={!canAddToCard()}
                  className="btn btn-primary"
                >
                  <i className="fa fa-shopping-cart me-1"></i> Add to cart
                </button>
              </div>
            </div>
          </div>

          <div className={"col-12"}>
            <h2>Product detail</h2>
            <Divider />
            <div dangerouslySetInnerHTML={{ __html: product.mota }}></div>
          </div>
        </div>
      </div>
    </div>
  );
};

export { UserProductDetail };
