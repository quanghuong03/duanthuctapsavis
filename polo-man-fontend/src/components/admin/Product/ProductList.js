import { useEffect, useState } from "react";
import {
  brandService,
  categoryService,
  materialService,
  productService,
} from "../../../service/admin";
import {
  Button,
  Dropdown,
  Form,
  Image,
  Input,
  Pagination,
  Popconfirm,
  Tabs,
  Select,
  Switch,
  Space,
  notification,
} from "antd";
import { toastService } from "../../../service/common";
import { Link } from "react-router-dom";
import { log } from "util";
import { selectSearchDataUtil } from "../../../utils";
import { SelectSearch } from "../../common/SelectSearch";
import { LoadingBox, LoadingPage } from "../../common";
import SearchBar from "../../../utils/Input/SearchBar";
import TitleCard from "../../../utils/Cards/TitleCard";

const { TabPane } = Tabs;

const tabs = [
  {
    key: "all",
    label: "Tất cả",
    status: null,
  },
  {
    key: "1",
    label: "Đang hoạt động",
    status: [1, 3],
  },
  {
    key: "0",
    label: "Ngừng hoạt động",
    status: 0,
  },
  {
    key: "3",
    label: "Đang khuyến mại",
    status: 3,
  },
];

const ProductList = () => {
  const [products, setProducts] = useState([]);
  const [categoryMap, setCategoryMap] = useState({});
  const [brandMap, setBrandMap] = useState({});
  const [materialMap, setMaterialMap] = useState({});
  const [page, setPage] = useState(1);

  const [categoryOptions, setCategoryOptions] = useState([]);
  const [brandOptions, setBrandOptions] = useState([]);
  const [materialOptions, setMaterialOptions] = useState([]);
  const LIMIT = 10;
  const [activeTab, setActiveTab] = useState("all");
  const [keyword, setKeyword] = useState("");

  const [loading, setLoading] = useState(true);
  const [startIndex, setStartIndex] = useState(0); // Biến để tính toán số thứ tự

  const onPageChange = async (page) => {
    setPage(page);
    const newStartIndex = (page - 1) * LIMIT;
    setStartIndex(newStartIndex);
  };

  useEffect(() => {
    (async () => {
      try {
        const products = await getProducts({});
        const categoryRes = await categoryService.getAllCategory();
        const brandRes = await brandService.getAllBrands();

        const materialRes = await materialService.getAllMaterial();
        const categoryMap = categoryRes.data.reduce((acrr, pre) => {
          acrr = {
            ...acrr,
            [pre.id]: pre.name,
          };
          return acrr;
        }, {});
        const brandMap = brandRes.data.reduce((acrr, pre) => {
          acrr = {
            ...acrr,
            [pre.id]: pre.name,
          };
          return acrr;
        }, {});
        const materialMap = materialRes.data.reduce((acrr, pre) => {
          acrr = {
            ...acrr,
            [pre.id]: pre.name,
          };
          return acrr;
        }, {});
        setCategoryOptions(
          selectSearchDataUtil.transformSearchSelectData(
            categoryRes.data,
            "id",
            "name"
          )
        );
        setBrandOptions(
          selectSearchDataUtil.transformSearchSelectData(
            brandRes.data,
            "id",
            "name"
          )
        );
        setMaterialOptions(
          selectSearchDataUtil.transformSearchSelectData(
            materialRes.data,
            "id",
            "name"
          )
        );

        setProducts(products);
        setCategoryMap(categoryMap);
        setBrandMap(brandMap);
        setMaterialMap(materialMap);
        setLoading(false);
      } catch (e) {
        toastService.error(e.apiMessage);
      }
    })();
  }, []);

  const handleSwitchChange = async (productId) => {
    try {
      setLoading(true);
      const currentProduct = products.find((p) => p.id === productId);
      const updatedStatus =
        currentProduct.status === 1 || currentProduct.status === 3 ? 0 : 1;

      // Call your API to update the product status based on your logic
      await productService.changeStatus(productId);

      // Update the local state to reflect the changes
      setProducts((prevProducts) =>
        prevProducts.map((p) =>
          p.id === productId ? { ...p, status: updatedStatus } : p
        )
      );

      notification.success({
        message: "Success",
        description: `Thay đổi trạng thái thành công`,
      });
      setLoading(false);
    } catch (error) {
      console.log(error);
    }
  };

  async function getProducts(form) {
    setLoading(true);
    try {
      const { data } = await productService.getAllProductsForAdmin({
        ...form,
        name: keyword,
      });
      console.log("API Response:", data); // In ra response của API
      setLoading(false);
      return data;
    } catch (error) {
      console.error("Error fetching products:", error);
      setLoading(false);
      throw error;
    }
  }

  const handleTabChange = (key) => {
    setActiveTab(key);
  };

  const filteredProducts =
    activeTab === "all"
      ? products
      : products.filter((item) => {
          if (activeTab === "1") {
            // Hiển thị sản phẩm có status là 1 hoặc 3
            return [1, 3].includes(item.status);
          } else {
            // Hiển thị sản phẩm có status bằng giá trị của activeTab
            return item.status === parseInt(activeTab, 10);
          }
        });

  const handleDelete = async (productId) => {
    try {
      setLoading(true);
      // await productService.deleteProductById(productId);
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

  return (
    <div
      style={{
        background: "#fff",
        borderRadius: "12px",
        boxShadow: "0 0 10px rgba(0, 0, 0, 0.5)",
      }}
    >
      <Tabs activeKey={activeTab} onChange={handleTabChange}>
        {tabs.map((tab) => (
          <TabPane tab={tab.label} key={tab.key} />
        ))}
      </Tabs>
      <h4
        style={{
          textAlign: "center",
          fontSize: "20px",
          fontWeight: "bolder",
          paddingTop: "35px",
          fontFamily: "revert",
        }}
      >
        DANH SÁCH SẢN PHẨM
      </h4>
      <Form
        layout={"inline"}
        className="my-3"
        // onFinish={onSearchHandle}
        style={{
          marginTop: "30px",
          marginLeft: "50px",
        }}
      >
        <Form.Item name={"name"}>
          <Input
            placeholder={"Tên sản phẩm"}
            value={keyword}
            onChange={(e) => setKeyword(e.target.value)}
          />
        </Form.Item>
        <Form.Item name={"categoryId"} style={{ width: "166px" }}>
          <SelectSearch
            allowClear
            placeholder={"Danh mục sản phẩm"}
            options={categoryOptions}
          />
        </Form.Item>
        <Form.Item name={"brandId"} style={{ width: "166px" }}>
          <SelectSearch
            allowClear
            placeholder={"Thương hiệu sản phẩm"}
            options={brandOptions}
          />
        </Form.Item>
        <Form.Item name={"materialId"} style={{ width: "166px" }}>
          <SelectSearch
            allowClear
            placeholder={"Chất liệu sản phẩm"}
            options={materialOptions}
          />
        </Form.Item>

        <Form.Item>
          <button htmlType={"submit"}>Search</button>
        </Form.Item>
        <Link to={"/admin/product/add"}>
          <button className="">Thêm sản phẩm</button>
        </Link>
      </Form>
      <p style={{ fontWeight: "bolder", marginLeft: "50px" }}>
        Tổng số sản phẩm: {filteredProducts.length}
      </p>
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
      <div style={{ maxHeight: "500px", overflowY: "auto" }}>
      {!loading && (
        <table className="table__main">
          <thead>
            <tr>
              <th>STT</th>
              <th>Tên</th>
              <th>Ảnh</th>
              <th>Danh mục</th>
              <th>Thương hiệu</th>
              <th>Chất Liệu</th>
              <th>Trạng thái</th>
              <th>Thao tác</th>
            </tr>
          </thead>
          <tbody>
            {filteredProducts
              .slice((page - 1) * LIMIT, page * LIMIT)
              .map((p, index) => {
                return (
                  <tr key={p.id}>
                    <td>{startIndex + index + 1}</td>

                    <td style={{ wordWrap:"break-word" }}>{p.name}</td>
                    <td
                      style={{
                        display: "flex",
                        justifyContent: "center",
                      }}
                    >
                      <Image src={p.image} width={80} height={100}></Image>
                    </td>
                    <td>{categoryMap[p.categoryId]}</td>
                    <td>{brandMap[p.brandId]}</td>
                    <td>{materialMap[p.materialId]}</td>
                    <td>
                      <Switch
                        checked={p.status === 1 || p.status === 3}
                        onChange={() => handleSwitchChange(p.id)}
                        style={{
                          width: "30px",
                          backgroundColor:
                            p.status === 1 || p.status === 3
                              ? "#4CAF50"
                              : "#FF0000",
                        }}
                      />
                    </td>
                    <td>
                      <div
                        className="actions"
                        style={{ display: "flex", alignItems: "center" }}
                      >
                        <div className="action">
                          <Link to={`/admin/product/update/${p.id}`}>
                            <button type="primary" className="btn">
                              <i className="fa-regular fa-pen-to-square"></i>
                            </button>
                          </Link>
                        </div>
                      </div>
                    </td>
                  </tr>
                );
              })}
          </tbody>
        </table>
      )}</div>
      <Pagination
        current={page}
        total={filteredProducts.length}
        pageSize={LIMIT}
        onChange={onPageChange}
        style={{ textAlign: "center" }}
      />
    </div>
  );
};

export { ProductList };
