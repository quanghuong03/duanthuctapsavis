import { useEffect, useState } from "react";
import { useUserStore } from "../../../layout/store";
import { useLocation } from "react-router-dom";
import {
  chatLieuSerivce,
  sanphamService,
  DongspService,
  thuonghieuService,
} from "../../../service/admin";
import { toastService } from "../../../service/common";
import { usePagination } from "../../../hook";
import { Button, Divider, Form, Input, Pagination, Slider } from "antd";
import { ProductBox } from "../Product/ProductBox";
import "./SearchProduct.css";
import { SelectSearch } from "../../common/SelectSearch";
import { selectSearchDataUtil } from "../../../utils";
import { EmptyPage } from "../../common";

const SearchProduct = () => {
  const [state, dispatch] = useUserStore();
  const location = useLocation();

  const [products, setProducts] = useState([]);
  const pagination = usePagination(products, 5);
  const [loading, setLoading] = useState(true);

  const [brandOptions, setBrandOptions] = useState([]);
  const [categoryOptions, setCategoryOptions] = useState([]);
  const [materialOptions, setMaterialOptions] = useState([]);

  useEffect(() => {
    (async () => {
      try {
        setLoading(true);
        const { tensanpham } = location.state;
        const productRes = await sanphamService.getAllSanPham({
          tensanpham,
        });
        const thuonghieuRes = await thuonghieuService.getThuongHieu();
        const dongspRes = await DongspService.getdongsp();
        const chatlieuRes = await chatLieuSerivce.getChatLieu();
        setLoading(false);

        setProducts(productRes.data);
        setBrandOptions(
          selectSearchDataUtil.transformSearchSelectData(
            thuonghieuRes.data,
            "mathuonghieu",
            "tenthuonghieu"
          )
        );
        setCategoryOptions(
          selectSearchDataUtil.transformSearchSelectData(
            dongspRes.data,
            "madongsp",
            "tendongsp"
          )
        );
        setMaterialOptions(
          selectSearchDataUtil.transformSearchSelectData(
            chatlieuRes.data,
            "machatlieu",
            "tenchatlieu"
          )
        );
      } catch (error) {
        toastService.error(error.apiMessage);
      }
    })();
  }, [location]);

  if (!products || products.length === 0) {
    return <EmptyPage description={"No Products Found"} />;
  }

  const searchHandle = async (form) => {
    try {
      const productRes = await sanphamService.getAllSanPham({
        ...form,
        tensanpham: location.state.tensanpham,
      });
      setProducts(productRes.data);
      pagination.jump(1);
    } catch (error) {
      toastService.error(error.apiMessage);
    }
  };

  return (
    <div className="search-product-page">
      <div className="breadcrumb-section">
        <div className="container">
          <h2>Product List</h2>
        </div>
      </div>
      <div className="container" style={{ padding: "70px 0" }}>
        <div className="row">
          <div className="col-3 search-product-form">
            <div>
              <h2>Filter Products</h2>

              <h4>
                Search for keyword
                <br />
                <span className="mt-3" style={{ lineHeight: "62px" }}>
                  <strong>{location.state.tensanpham}</strong>
                </span>
              </h4>
            </div>

            <Divider />
            <Form layout="vertical" onFinish={searchHandle}>
              <Form.Item name={"mathuonghieu"} label="Thương Hiệu">
                <SelectSearch placeholder="Brand" options={brandOptions} />
              </Form.Item>
              <Form.Item name={"machatlieu"} label="Catgory">
                <SelectSearch
                  placeholder="Chất Liệu"
                  options={categoryOptions}
                />
              </Form.Item>
              <Form.Item name={"madongsp"} label="Kiểu áo">
                <SelectSearch
                  placeholder="Material"
                  options={materialOptions}
                />
              </Form.Item>
              <Form.Item name={"minPrice"} label="Min Price">
                <Input placeholder="Min Price" type="number" />
              </Form.Item>
              <Form.Item name={"maxPrice"} label="Max Price">
                <Input placeholder="Max Price" type="number" />
              </Form.Item>
              <Form.Item>
                <Button htmlType="submit" type="primary">
                  Filter
                </Button>
              </Form.Item>
            </Form>
          </div>
          <div className="col-9">
            <div className="product-list d-flex">
              {pagination.currentData().map((sanpham) => {
                return <ProductBox key={sanpham.masanpham} sanpham={sanpham} />;
              })}
            </div>

            <div
              className="pagination"
              style={{ display: "flex", justifyContent: "center" }}
            >
              <Pagination
                onChange={(e) => pagination.jump(e)}
                total={products?.length}
                current={pagination.getCurrentPage()}
                pageSize={pagination.getItemPerPage()}
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export { SearchProduct };
