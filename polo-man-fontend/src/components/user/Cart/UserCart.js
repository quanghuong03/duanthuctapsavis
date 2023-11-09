import { Button, Checkbox, Input, Popconfirm } from "antd";
import "./UserCart.css";
import { useEffect, useState } from "react";
import { giohangService } from "../../../service/user";
import { toastService } from "../../../service/common";
import { Link, useNavigate } from "react-router-dom";
import { DebounceInput, EmptyPage, LoadingPage } from "../../common";
import { useNavigateLoginPage } from "../../../hook";

const UserCart = () => {
  const [products, setProducts] = useState([]);
  const navigate = useNavigate();
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    (async () => {
      try {
        const res = await giohangService.getProducts();
        setProducts(
          res.data.list.map((p) => {
            return {
              ...p,
              checked: p.tinhtrang === 0 ? true : false,
            };
          })
        );
        setLoading(false);
        console.log(res);
      } catch (error) {
        toastService.error(error.apiMessage);
      }
    })();
  }, []);

  if (loading) {
    return <LoadingPage />;
  }

  if (!products || products.length === 0) {
    return <EmptyPage description={"No cart item"} />;
  }

  const getTotalPrice = () => {
    if (products?.length === 0) {
      return 0;
    }
    return products
      .filter((p) => p.checked)
      .reduce((acrr, pre) => {
        return (acrr += pre.giaban * pre.soluong);
      }, 0);
  };

  const handleDelete = async (product) => {
    try {
      await giohangService.updatesoluong(product.magiohangchitiet, 0);
      setProducts((pre) => {
        const newProducts = pre.filter(
          (p) => p.magiohangchitiet !== product.magiohangchitiet
        );
        return newProducts;
      });
    } catch (error) {
      toastService.error(error.apiMessage);
    }
  };

  const quantityChangeHandle = async (product, e) => {
    try {
      const newQuantity = e.target.value;
      if (!newQuantity || newQuantity == 0) {
        return;
      }
      await giohangService.updatesoluong(product.magiohangchitiet, newQuantity);
      setProducts((pre) => {
        return pre.map((p) => {
          if (p.magiohangchitiet === product.magiohangchitiet) {
            return {
              ...product,
              soluong: +newQuantity,
            };
          }
          return p;
        });
      });
    } catch (error) {
      toastService.error(error.apiMessage);
    }
  };

  const checkProductChangeHandle = async (product, e) => {
    try {
      product.checked = e.target.checked;
      await giohangService.updateStatus(
        product.magiohangchitiet,
        product.checked ? 0 : 1
      );
      setProducts([...products]);
    } catch (error) {
      toastService.error(error.apiMessage);
    }
  };

  const checkOutClickHandle = async () => {
    if (!products?.some((p) => p.checked)) {
      toastService.info("Please choose at least one product");
      return;
    }

    navigate("/checkout");
  };

  const checkAllProduct = async (e) => {
    if (e.target.checked) {
      for (let product of products) {
        giohangService.updateStatus(product.magiohangchitiet, 0);
      }
      const newProducts = products.map((product) => {
        product.checked = true;
        return product;
      });
      console.log(newProducts);
      setProducts([...newProducts]);
    }

    if (!e.target.checked) {
      for (let product of products) {
        giohangService.updateStatus(product.magiohangchitiet, 1);
      }
      const newProducts = products.map((product) => {
        product.checked = false;
        return product;
      });
      console.log(newProducts);
      setProducts([...newProducts]);
    }
  };

  return (
    <div className="cart">
      <div className="breadcrumb-section">
        <div className="container"></div>
      </div>
      <div className="carts">
        <div className="container">
          <div className="col-sm-12 table-responsive-xs">
            <table className="table cart-table">
              <thead>
                <tr className="table-head">
                  <th style={{ display: "flex" }}>
                    <Checkbox
                      checked={products.every((p) => p.checked)}
                      onChange={(e) => checkAllProduct(e)}
                    ></Checkbox>
                  </th>
                  <th>image</th>
                  <th>Tên sản phẩm</th>
                  <th>Giá</th>
                  <th>Số Lượng</th>
                  <th>Tổng Gía</th>
                  <th>action</th>
                </tr>
              </thead>
              <tbody>
                {products.map((p, index) => {
                  return (
                    <tr key={index}>
                      <td>
                        <Checkbox
                          checked={p.checked}
                          onChange={(e) => checkProductChangeHandle(p, e)}
                        ></Checkbox>
                      </td>
                      <td>
                        <a href="#">
                          <img src={`/img/sanpham/${p.hinhanh}`} alt="" />
                        </a>
                      </td>
                      <td>
                        {p.tensanpham}
                        <br />
                        <strong>
                          Màu : {p.tenmau} - Size : {p.sosize}
                        </strong>
                      </td>
                      <td>{p.giaban} VND</td>
                      <td>
                        <DebounceInput
                          min={1}
                          type="number"
                          onSubmit={(e) => quantityChangeHandle(p, e)}
                          defaultValue={p.soluong}
                        />
                      </td>
                      <td>
                        <h4 className="td-color">{p.giaban * p.soluong} VND</h4>
                      </td>
                      <td>
                        <Popconfirm
                          title="Xóa đơn hàng"
                          description="Bạn có chắc chắn muốn xóa đơn hàng này?"
                          onConfirm={() => handleDelete(p)}
                          // onCancel={cancel}
                          okText="Có"
                          cancelText="Không"
                        >
                          <Button>
                            <i className="fa fa-close"></i>
                          </Button>
                        </Popconfirm>
                      </td>
                    </tr>
                  );
                })}
              </tbody>
            </table>

            <div className="table-responsive-md">
              <table className="table cart-table ">
                <tfoot>
                  <tr>
                    <td>Total price :</td>
                    <td>
                      <h3>{getTotalPrice()}</h3>
                    </td>
                  </tr>
                </tfoot>
              </table>
            </div>
          </div>

          <div className="d-flex justify-content-end">
            <div className="d-flex justify-content-end">
              <button
                onClick={() => checkOutClickHandle()}
                className="btn btn-primary"
              >
                Check out
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export { UserCart };
