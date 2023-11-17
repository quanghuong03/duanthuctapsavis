import { useEffect, useState } from "react";
import {
  chatLieuSerivce,
  sanphamService,
  DongspService,
  thuonghieuService,
} from "../../../../service/admin";
import {
  Button,
  Dropdown,
  Form,
  Image,
  Input,
  Pagination,
  Popconfirm,
  Select,
  Space,
} from "antd";
import { toastService } from "../../../../service/common";
import { Link } from "react-router-dom";
import { log } from "util";
import { selectSearchDataUtil } from "../../../../utils";
import { SelectSearch } from "../../../common/SelectSearch";
import { LoadingBox, LoadingPage } from "../../../common";

const ProductList = () => {
  const [products, setProducts] = useState([]);
  const [categoryMap, setCategoryMap] = useState({});
  const [brandMap, setBrandMap] = useState({});
  const [chatlieumap, setChatLieuMap] = useState({});
  const [page, setPage] = useState(1);

  const [categoryOptions, setCategoryOptions] = useState([]);
  const [brandOptions, setBrandOptions] = useState([]);

  const [materialOptions, setMaterialOptions] = useState([]);
  const LIMIT = 10;

  const [loading, setLoading] = useState(true);

  const onPageChange = async (e) => {
    setPage(e);
  };

  useEffect(() => {
    (async () => {
      try {
        const products = await getProducts({});
        const categoryRes = await DongspService.getdongsp();
        const brandRes = await thuonghieuService.getThuongHieu();

        const materialRes = await chatLieuSerivce.getChatLieu();
        const categoryMap = categoryRes.data.reduce((acrr, pre) => {
          acrr = {
            ...acrr,
            [pre.madongsp]: pre.tendongsp,
          };
          return acrr;
        }, {});
        const brandMap = brandRes.data.reduce((acrr, pre) => {
          acrr = {
            ...acrr,
            [pre.mathuonghieu]: pre.tenthuonghieu,
          };
          return acrr;
        }, {});
        const chatlieumap = materialRes.data.reduce((acrr, pre) => {
          acrr = {
            ...acrr,
            [pre.machatlieu]: pre.tenchatlieu,
          };
          return acrr;
        }, {});
        setCategoryOptions(
          selectSearchDataUtil.transformSearchSelectData(
            categoryRes.data,
            "madongsp",
            "tendongsp"
          )
        );
        setBrandOptions(
          selectSearchDataUtil.transformSearchSelectData(
            brandRes.data,
            "mathuonghieu",
            "tenthuonghieu"
          )
        );
        setMaterialOptions(
          selectSearchDataUtil.transformSearchSelectData(
            materialRes.data,
            "machatlieu",
            "tenchatlieu"
          )
        );

        setProducts(products);
        setCategoryMap(categoryMap);
        setBrandMap(brandMap);
        setLoading(false);
        setChatLieuMap(chatlieumap);
      } catch (e) {
        toastService.error(e.apiMessage);
      }
    })();
  }, []);

  async function getProducts(form) {
    setLoading(true);
    const { data } = await sanphamService.getAllSanPham(form);
    setLoading(false);
    return data;
  }

  const handleDelete = async (productId) => {
    try {
      setLoading(true);
      //   await productService.deleteProductById(productId);
      toastService.success("Xóa sản phẩm thành công");
      setProducts(products.filter((p) => p.id != productId));
      if (products?.length === 0) {
        setPage(1);
      }
      setLoading(false);
    } catch (error) {
      console.log(error);
    }
  };

  async function onSearchHandle(form) {
    const products = await getProducts(form);
    setProducts(products);
    setPage(1);
  }

  return (
    <div>
      <Form layout={"inline"} className="my-3" onFinish={onSearchHandle}>
        <Form.Item name={"tensanpham"}>
          <Input placeholder={"Tên sản phẩm"} />
        </Form.Item>
        <Form.Item name={"madongsp"} style={{ width: "166px" }}>
          <SelectSearch
            allowClear
            placeholder={"Danh mục sản phẩm"}
            options={categoryOptions}
          />
        </Form.Item>
        <Form.Item name={"mathuonghieu"} style={{ width: "166px" }}>
          <SelectSearch
            allowClear
            placeholder={"Thương hiệu sản phẩm"}
            options={brandOptions}
          />
        </Form.Item>
        <Form.Item name={"machatlieu"} style={{ width: "166px" }}>
          <SelectSearch
            allowClear
            placeholder={"Chất liệu sản phẩm"}
            options={materialOptions}
          />
        </Form.Item>

        {/* <Form.Item name={'sizeId'} style={{width: '250px'}}>
                <SelectSearch allowClear placeholder={'Kích thước'} options={sizeOptions}/>
            </Form.Item>
            <Form.Item name={'colorId'} style={{width: '250px'}}>
                <SelectSearch allowClear placeholder={'Màu sắc'} options={colorOptions}/>
            </Form.Item> */}

        <Form.Item>
          <Button htmlType={"submit"} type={"primary"}>
            Search
          </Button>
        </Form.Item>
      </Form>
      <Link to={"/admin/sanpham/add"}>
        <Button type="primary">Thêm sản phẩm</Button>
      </Link>
      {loading && (
        <div
          style={{
            height: "500px",
            display: "flex",
            justifyContent: "center",
          }}
        >
          <LoadingBox />
        </div>
      )}
      {!loading && (
        <table className="mt-3 table table-bordered">
          <thead>
            <tr>
              <th>Id</th>

              <th>Tên</th>
              <th style={{ width: "70px" }}>Ảnh</th>
              <th>Danh mục</th>
              <th>Thương hiệu</th>
              <th>Chất Liệu</th>
              <th>Giá bán</th>
              <th>Thao tác</th>
            </tr>
          </thead>
          <tbody>
            {products
              .slice((page - 1) * LIMIT, (page - 1) * LIMIT + LIMIT)
              .map((p) => {
                return (
                  <tr key={p.masanpham}>
                    <td>{p.masanpham}</td>

                    <td>{p.tensanpham}</td>
                    <td
                      style={{
                        display: "flex",
                        justifyContent: "center",
                        width: "70px",
                      }}
                    >
                      <Image src={p.hinhanh} width={50} height={50}></Image>
                    </td>
                    <td>{p.tendongsp}</td>
                    <td>{p.tenthuonghieu}</td>
                    <td>{p.tenchatlieu}</td>
                    <td>{p.giaban}</td>
                    <td>
                      <div className="actions">
                        <div className="action update">
                          <Link to={"/admin/sanpham/update/" + p.masanpham}>
                            <button className="btn">
                              <i className="fa-regular fa-pen-to-square"></i>
                            </button>
                          </Link>
                        </div>
                        <div className="action delete">
                          <Popconfirm
                            title="Xóa sản phẩm"
                            description="Bạn có chắc chắn muốn xóa sản phẩm này?"
                            onConfirm={() => handleDelete(p.masanpham)}
                            // onCancel={cancel}
                            okText="Có"
                            cancelText="Không"
                          >
                            <button className="btn">
                              <i className="fa-sharp fa-solid fa-trash"></i>
                            </button>
                          </Popconfirm>
                        </div>
                      </div>
                    </td>
                  </tr>
                );
              })}
          </tbody>
        </table>
      )}
      <Pagination
        defaultCurrent={1}
        total={products.length}
        pageSize={LIMIT}
        onChange={onPageChange}
      />
      ;
    </div>
  );
};

export { ProductList };
