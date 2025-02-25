import { useEffect, useState } from "react";
import { customerService } from "../../../service/admin";
import { Pagination, Popconfirm } from "antd";
import { Link } from "react-router-dom";

const CustomerList = () => {
  const [customer, setCustomer] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [pageSize, setPageSize] = useState(4);
  const [totalCustomers, setTotalCustomers] = useState(0);

  useEffect(() => {
    (async () => {
      const body = await customerService.getAllCustomer();
      const customers = body.data;
      setTotalCustomers(customers.length);
      const startIndex = (currentPage - 1) * pageSize;
      const slicedCustomers = customers.slice(
        startIndex,
        startIndex + pageSize
      );
      setCustomer(slicedCustomers);
    })();
  }, [currentPage, pageSize]);

  const handleChangePage = (page) => {
    setCurrentPage(page);
  };

  return (
    <div
      style={{
        background: "#fff",
        borderRadius: "12px",
        boxShadow: "0 0 10px rgba(0, 0, 0, 0.5)",
      }}
    >
      <br></br>
      <Link to={"/admin/customer/add"}>
        <button type="primary" className="btn-customer__add">
          Thêm khách hàng
          <i className="fas fa-plus" style={{ paddingLeft: "10px" }}></i>
        </button>
      </Link>
      <br></br>
      <br></br>
      <div className="table__main--customer">
        <div style={{ maxHeight: "600px", overflowY: "auto" }}>
          <table>
            <thead>
              <tr>
                <th>STT</th>
                <th>Họ Và Tên</th>
                <th>Email</th>
                <th>Số điện thoại</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {customer.map((customer, index) => {
                return (
                  <tr key={customer.id}>
                    <td style={{ paddingLeft: "60px" }}>
                      {(currentPage - 1) * pageSize + index + 1}{" "}
                    </td>
                    <td>
                      <div className="flex items-center space-x-3">
                        <div className="avatar">
                          <div className="mask mask-squircle w-12 h-12">
                            <img
                              src={customer.avatar}
                              alt="Avatar"
                              className="rounded-full"
                              style={{
                                width: "50px",
                                height: "50px",
                                objectFit: "cover",
                              }}
                            />
                          </div>
                        </div>
                        <div>
                          <div className="font-bold">{customer.name}</div>
                        </div>
                      </div>
                    </td>
                    <td>{customer.email}</td>
                    <td>{customer.phone}</td>
                    <td>
                      <div
                        className="actions"
                        style={{ display: "flex", alignItems: "center" }}
                      >
                        <div className="action">
                          <Link to={`/admin/customer/update/${customer.id}`}>
                            <button type="primary" className="btn">
                              <i className="fa-regular fa-pen-to-square"></i>
                            </button>
                          </Link>
                        </div>
                        <div className="action">
                          <Popconfirm
                            title="Xoá khách hàng"
                            description="Bạn có chắc chắn muốn xoá khách hàng này?"
                            okText="Xoá"
                            cancelText="Huỷ"
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
        </div>
      </div>
      <div style={{ textAlign: "center", marginTop: "20px" }}>
        <Pagination
          current={currentPage}
          pageSize={pageSize}
          total={totalCustomers}
          onChange={handleChangePage}
        />
      </div>
    </div>
  );
};

export { CustomerList };
