import { useEffect, useState } from "react";
import { categoryService } from "../../../service/admin";
import { Pagination, Button, Tabs, Form, Input, Switch } from "antd";
import { Link } from "react-router-dom";
import { AddCategory } from "./AddCategorie/AddCategori";
import { toastService } from "../../../service/common";
import "../admin-product.css";

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
    status: 1,
  },
  {
    key: "0",
    label: "Ngừng hoạt động",
    status: 0,
  },
];

const CategoryList = () => {
  const [searchProductName, setSearchProductName] = useState("");
  const [category, setCategory] = useState([]);
  const [activeTab, setActiveTab] = useState("all");
  const [showCategoryModal, setShowCategoryModal] = useState(false);
  const [form] = Form.useForm();
  const [refreshList, setRefreshList] = useState(false);
  const [page, setPage] = useState(1);
  const LIMIT = 5;
  const [startIndex, setStartIndex] = useState(0);

  const fetchData = async () => {
    const body = await categoryService.getAllCategory();
    setCategory(body.data);
  };

  useEffect(() => {
    fetchData();
  }, [refreshList]);

  const filteredProducts =
    activeTab === "all"
      ? category
      : category.filter((item) => {
          if (activeTab === "1") {
            // Hiển thị sản phẩm có status là 1 hoặc 3
            return [1, 3].includes(item.status);
          } else {
            // Hiển thị sản phẩm có status bằng giá trị của activeTab
            return item.status === parseInt(activeTab, 10);
          }
        });

  const onPageChange = async (page) => {
    setPage(page);
    const newStartIndex = (page - 1) * LIMIT;
    setStartIndex(newStartIndex);
  };

  const handleTabChange = (key) => {
    setActiveTab(key);
  };

  const filteredCategorys = category.filter((item) => {
    const statusMatch =
      activeTab === "all" || item.status === parseInt(activeTab, 10);

    const nameMatch =
      item.name &&
      item.name.toLowerCase().includes(searchProductName?.toLowerCase() || "");

    const descriptionMatch =
      item.description &&
      item.description
        .toLowerCase()
        .includes(searchProductName?.toLowerCase() || "");

    const indexMatch = (item.index + 1)
      .toString()
      .includes(searchProductName?.toString() || "");

    return statusMatch && (nameMatch || descriptionMatch || indexMatch);
  });

  const handleDelete = async (id) => {
    const body = await categoryService.changeStatus(id);

    toastService.info("Thay đổi trạng thái thành công ");
  };

  async function toggleStatus(id, currentStatus) {
    const newStatus = currentStatus === 1 ? 0 : 1;
    await categoryService.changeStatus(id, newStatus);

    // Update the local state with the new status
    setCategory((prevCategorys) =>
      prevCategorys.map((category) =>
        category.id === id ? { ...category, status: newStatus } : category
      )
    );
  }

  const handleList = () => {
    setShowCategoryModal(false);
    setRefreshList((prevState) => !prevState);
    fetchData();
  };

  return (
    <div
      style={{
        background: "#fff",
        borderRadius: "12px",
        boxShadow: "0 0 10px rgba(0, 0, 0, 0.5)",
      }}
    >
      <AddCategory
        open={showCategoryModal}
        onCreateCategoryFinish={handleList}
        onCancel={() => setShowCategoryModal(false)}
      />
      <Tabs activeKey={activeTab} onChange={handleTabChange}>
        {tabs.map((tab) => (
          <TabPane tab={tab.label} key={tab.key} />
        ))}
      </Tabs>
      <br />
      <div style={{ display: "flex", marginLeft: "0px" }}>
        <button
          onClick={() => setShowCategoryModal(true)}
          type="primary"
          className="btn-customer__add "
        >
          Thêm loại áo
          <i className="fas fa-plus" style={{ paddingLeft: "10px" }}></i>
        </button>
        <p style={{ fontWeight: "bolder", fontSize: "20px" }}>Tìm kiếm:</p>
        <Input
          style={{ width: "300px", marginLeft: "30px" }}
          placeholder="Search by product name..."
          value={searchProductName}
          onChange={(e) => setSearchProductName(e.target.value)}
        />
      </div>
      <br />
      <br />
      <div className="table__main">
        <table>
          <thead>
            <tr>
              <th>STT</th>
              <th>Tên loại áo</th>
              <th>Mô tả</th>
              <th>Trạng thái</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {filteredCategorys.length === 0 ? (
              <tr>
                <td colSpan="5">Không có giá trị.</td>
              </tr>
            ) : (
              filteredCategorys
                .slice((page - 1) * LIMIT, page * LIMIT)
                .map((category, index) => (
                  <tr key={category.id}>
                    <td style={{ paddingLeft: "60px" }}>
                      {startIndex + index + 1}
                    </td>
                    <td>{category.name}</td>
                    <td>{category.description}</td>
                    <td>
                      <Switch
                        checked={category.status === 1}
                        onChange={() =>
                          toggleStatus(category.id, category.status)
                        }
                        style={{
                          backgroundColor:
                            category.status === 1 ? "green" : "red",
                          width: "30px",
                        }}
                      />
                    </td>
                    <td>
                      <div
                        className="actions"
                        style={{ display: "flex", alignItems: "center" }}
                      >
                        <div className="action">
                          <Link to={`/admin/category/update/${category.id}`}>
                            <button type="primary" className="btn">
                              <i className="fa-regular fa-pen-to-square"></i>
                            </button>
                          </Link>
                        </div>
                      </div>
                    </td>
                  </tr>
                ))
            )}
          </tbody>
        </table>
      </div>
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

export { CategoryList };
