import { useEffect, useState } from "react";
import //   addressService,
//   orderService,
//   productDetailService,
//   productService,
"../../../../service/admin";
import { selectSearchDataUtil } from "../../../../utils";
import { SelectSearch } from "../../../common/SelectSearch";
import {
  Form,
  Input,
  List,
  Avatar,
  Card,
  Space,
  Image,
  Select,
  Button,
} from "antd";
import { toastService } from "../../../../service/common";
import { Typography } from "antd";
import { DebounceInput } from "../../../common";
import { Spin } from "antd";
import "./AddOrder.css";
import { v4 as uuid } from "uuid";
import { useNavigate } from "react-router-dom";
const { Title } = Typography;

export const AddOrder = () => {
  const [province, setProvince] = useState(null);
  const [district, setDistrict] = useState(null);
  const [ward, setWard] = useState(null);
  const [provinceOptions, setProvinceOptions] = useState([]);
  const [districtOptions, setDistrictOptions] = useState([]);
  const [wardOptions, setWardOptions] = useState([]);
  const [addressForm] = Form.useForm();

  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [searchSelectForcus, setSearchSelectForcus] = useState(false);
  const [selectedProducts, setSelectedProducts] = useState([]);
  const [productDetails, setProductDetails] = useState({});

  const navigate = useNavigate();

  useEffect(() => {
    (async () => {
      try {
        // const provinceRes = await addressService.getProvinces();
        const provinceOptions = selectSearchDataUtil.transformSearchSelectData(
          //   provinceRes.data,
          "code",
          "province"
        );
        setProvinceOptions(provinceOptions);
      } catch (error) {
        toastService.error(error.apiMessage);
      }
    })();
  }, []);

  const provinceChangeHandle = async (province) => {
    // const districtRes = await addressService.getDistricts(province);
    setDistrictOptions(
      selectSearchDataUtil.transformSearchSelectData(
        // districtRes.data,
        "district",
        "code"
      )
    );
    addressForm.resetFields(["district", "ward"]);
    setProvince(province);
    setDistrict(null);
    setWard(null);
  };

  const onDistrictChangeHandle = async (district) => {
    // const wardRes = await addressService.getWards(district);
    setWardOptions(
      selectSearchDataUtil.transformSearchSelectData(
        // wardRes.data,
        "ward",
        "code"
      )
    );
    addressForm.resetFields(["ward"]);
    setDistrict(district);
    setWard(null);
  };

  const onWardChangeHandle = (ward) => {
    setWard(wardOptions.find((o) => o.key === ward).label);
  };

  const handleSearchProduct = async (e) => {
    if (loading) {
      return;
    }
    const isLoading = true;
    setProducts([]);
    setLoading(isLoading);

    // const products = await productService.getAllProducts({
    //   name: e.target.value,
    // });
    setProducts(products.data);
    setLoading(false);
  };

  const selectProductHandle = async (product) => {
    setSearchSelectForcus(false);
    // const { data } = await productDetailService.getAllProductDetailsByProductId(
    //   product.id
    // );
    // const productDetailOptions = data.map((pd) => {
    //   return {
    //     value: pd.product_detail_id,
    //     label: `${pd.name_size}_${pd.name_color}`,
    //   };
    // });

    // const products = selectedProducts.filter((p) => p.id === product.id);
    // if (products.length >= data.length) {
    //   toastService.error("Không thể thêm sản phẩm này do đã hết phân loại");
    //   return;
    // }
    // console.log(data);
    // const selectedIds = products.map((p) => p.productDetailIdSelected);
    // console.log(selectedIds);
    // const unSelectedIds = data
    //   .filter((pd) => !selectedIds.includes(pd.product_detail_id))
    //   .map((pd) => pd.product_detail_id);
    // console.log(unSelectedIds);
    // const defaultSelectProductId = unSelectedIds[0];

    // setSelectedProducts((pre) => {
    //   const newProduct = {
    //     ...product,
    //     quantity: 1,
    //     index: uuid(),
    //     maxQuantity: data?.[0].quantity,
    //     productDetailIdSelected: defaultSelectProductId,
    //     productDetailOptions,
    //     productDetails: data,
    //   };
    //   return [...pre, newProduct];
    // });
  };

  const addOrderHandle = async () => {
    try {
      await addressForm.validateFields();
    } catch (error) {
      return;
    }
    if (selectedProducts.length === 0) {
      toastService.error("Vui lòng thêm sản phẩm");
      return;
    }
    const addressValue = addressForm.getFieldsValue();
    const orderDetails = selectedProducts.map((sp) => {
      return {
        product_detail_id: sp.productDetailIdSelected,
        price: sp.price,
        quantity: sp.quantity,
      };
    });
    const totalPrice = orderDetails.reduce((acrr, pre) => {
      return (acrr += pre.quantity * pre.price);
    }, 0);
    const request = {
      ...addressValue,
      order_details: orderDetails,
      total_price: totalPrice,
    };
    try {
      //   const newOrder = await orderService.createOrder(request);
      toastService.success("Thêm đơn hàng thành công");
      navigate("/admin/orders");
    } catch (e) {
      toastService.error(e.apiMessage);
    }
  };

  const productDetailQuantityChangeHandle = (p, e) => {
    const quantity = +e.target.value;
    if (quantity > p.maxQuantity) {
      toastService.error("Số lượng sản phẩm đã đạt giới hạn");
      return;
    }
    setSelectedProducts(
      selectedProducts.map((sp) => {
        if ((sp = p)) {
          return {
            ...sp,
            quantity,
          };
        }
        return sp;
      })
    );
    p = {
      ...p,
      quantity,
    };
  };

  const deleteSelectedProductHandle = (deleteProduct) => {
    setSelectedProducts(
      selectedProducts.filter((p) => p.index != deleteProduct.index)
    );
  };

  const selectProductDetailHandle = (product, e) => {
    const isExist = selectedProducts.filter(
      (sp) => sp.index !== product.index && sp.productDetailIdSelected === e
    );
    console.log({ product, selectedProducts, isExist, e });
    if (isExist.length) {
      toastService.error("Đã có phân loại này");
      return;
    }

    const productDetail = product.productDetails.find(
      (pd) => pd.product_detail_id === e
    );
    if (productDetail.quantity <= 0) {
      toastService.error("Phân loại đã hết hàng");
      return;
    }

    setSelectedProducts(
      selectedProducts.map((sp) => {
        if (sp === product) {
          return {
            ...product,
            quantity: 1,
            maxQuantity: productDetail.quantity,
            productDetailIdSelected: e,
          };
        }
        return sp;
      })
    );
    console.log(e);
  };

  return (
    <div>
      <Title level={1}>Thêm đơn hàng</Title>
      <Title level={4}>Thông tin khách hàng</Title>
      <Form
        form={addressForm}
        layout="vertical"
        style={{ maxWidth: "700px", display: "flex", flexWrap: "wrap" }}
      >
        <Form.Item
          label="Tên khách hàng"
          rules={[]}
          className="my-3"
          name={"username"}
          style={{
            width: "300px",
            margin: "0 10px",
          }}
        >
          <Input placeholder={"Tên khách hàng"} />
        </Form.Item>
        <Form.Item
          label="Số điện thoại"
          className="my-3"
          name={"phone"}
          rules={[]}
          style={{
            width: "300px",
            margin: "0 10px",
          }}
        >
          <Input placeholder={"Số điện thoại"} />
        </Form.Item>
        <Form.Item
          label="Tỉnh/ Thành phố"
          className="my-3"
          name={"province"}
          rules={[]}
        >
          <SelectSearch
            placeholder={"Tỉnh/ Thành phố"}
            options={provinceOptions}
            onChange={provinceChangeHandle}
            style={{
              width: "300px",
              margin: "0 10px",
            }}
          ></SelectSearch>
        </Form.Item>

        <Form.Item
          label="Quận/ Huyện"
          className="my-3"
          name={"district"}
          rules={[]}
        >
          <SelectSearch
            placeholder={"Quận/ Huyện"}
            options={districtOptions}
            onChange={onDistrictChangeHandle}
            disabled={!province}
            style={{
              width: "300px",
              margin: "0 10px",
            }}
          ></SelectSearch>
        </Form.Item>
        <Form.Item
          label="Xã/ Thị trấn"
          className="my-3"
          name={"ward"}
          rules={[]}
        >
          <SelectSearch
            placeholder={"Xã/ Thị trấn"}
            options={wardOptions}
            disabled={!district}
            style={{
              width: "300px",
              margin: "0 10px",
            }}
          ></SelectSearch>
        </Form.Item>
        <Form.Item
          label="Ghi chú"
          className="my-3"
          name={"street"}
          style={{
            width: "300px",
            margin: "0 10px",
          }}
        >
          <Input placeholder={"Ghi chú"} />
        </Form.Item>
      </Form>
      <Title level={4}>Sản phẩm</Title>
      <div className="products" style={{ gap: "30px" }}>
        <div className="select_product_form" style={{ width: "600px" }}>
          <DebounceInput
            placeholder="Tìm kiếm sản phẩm"
            onFocus={() => setSearchSelectForcus(true)}
            className="search_products_input"
            disabled={loading}
            onSubmit={handleSearchProduct}
          />
          {loading && (
            <Card style={{ display: "flex", justifyContent: "center" }}>
              <Space size="middle">
                <Spin size="large" />
              </Space>
            </Card>
          )}
          {!loading && products.length > 0 && (
            <Card
              style={{
                display: searchSelectForcus ? "block" : "none",
              }}
            >
              <List className="search_products_list">
                {" "}
                {products.map((p) => {
                  return (
                    <List.Item
                      className="select_product_item"
                      key={p.id}
                      style={{ cursor: "pointer" }}
                      onClick={() => selectProductHandle(p)}
                    >
                      <List.Item.Meta
                        avatar={<Avatar src={p.image} />}
                        title={p.name}
                      ></List.Item.Meta>
                    </List.Item>
                  );
                })}
              </List>
            </Card>
          )}
        </div>
        <div className="selected_products mt-3" style={{ width: "600px" }}>
          {selectedProducts.length > 0 && (
            <table
              className="table table-bordered"
              style={{ minWidth: "800px" }}
            >
              <thead>
                <tr>
                  <th>Id</th>
                  <th>Ảnh</th>
                  <th>Tên sản phẩm</th>
                  <th>Phân loại</th>
                  <th>Số lượng</th>
                  <th>Số lượng còn trong kho</th>
                  <th>Giá</th>
                  <th>Thao tác</th>
                </tr>
              </thead>
              <tbody>
                {selectedProducts.map((p) => {
                  return (
                    <tr key={p.index}>
                      <td>{p.id}</td>
                      <td>
                        <Image width={50} height={50} src={p.image}></Image>
                      </td>
                      <td>{p.name}</td>
                      <td>
                        <Form.Item>
                          <SelectSearch
                            value={p?.productDetailIdSelected}
                            onChange={(e) => selectProductDetailHandle(p, e)}
                            style={{ width: "100px" }}
                            options={p?.productDetailOptions.map((pd) => {
                              return {
                                ...pd,
                              };
                            })}
                          />
                        </Form.Item>
                      </td>
                      <td>
                        <Input
                          style={{ width: "70px" }}
                          type="number"
                          value={p.quantity}
                          min={1}
                          onChange={(e) =>
                            productDetailQuantityChangeHandle(p, e)
                          }
                        />
                      </td>
                      <td>{p.maxQuantity}</td>
                      <td>{p.price}</td>
                      <td>
                        <Button onClick={() => deleteSelectedProductHandle(p)}>
                          Xóa
                        </Button>
                      </td>
                    </tr>
                  );
                })}
                <tr>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td>Tổng tiền</td>
                  <td>
                    <strong>
                      {selectedProducts.reduce((acrr, pre) => {
                        const price = pre.quantity * pre.price;
                        return (acrr += price);
                      }, 0)}
                    </strong>
                  </td>
                  <td></td>
                </tr>
              </tbody>
            </table>
          )}
        </div>
      </div>
      <Button type="primary" onClick={addOrderHandle}>
        Thêm đơn hàng
      </Button>
    </div>
  );
};
