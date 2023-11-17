import { useEffect, useState } from "react";
import { sizeService } from "../../../service/admin";
import "../ChatLieu/ChatLieuList.css";
import { Button, Popconfirm } from "antd";
import { Link } from "react-router-dom";

const SizeList = () => {
  const [size, setSize] = useState([]);
  useEffect(() => {
    (async () => {
      const body = await sizeService.getAllSize();
      console.log(body.data);
      setSize(body.data);
    })();
  }, []);
  return (
    <div>
      <Link to={"/admin/size/add"}>
        <Button type="primary">Thêm Size</Button>
      </Link>
      <br></br>
      <br></br>
      <table className="table">
        <thead>
          <tr>
            <th>Id</th>
                      <th>Số size</th>
                      <th>Chiều cao</th>
                      <th>Cân nặng</th>

            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {size.map((material) => {
            return (
              <tr key={material.masize}>
                <td>{material.masize}</td>
                    <td>{material.sosize}</td>
                    <td>{material.chieucao}</td>
                    <td>{material.cannang}</td>

                <td>
                  <div className="actions">
                    <div className="action update">
                      <Link
                        to={`/admin/size/update/${material.masize}`}
                      >
                        <Button type="primary" className="btn">
                          <i className="fa-regular fa-pen-to-square"></i>
                        </Button>
                      </Link>
                    </div>
                    <div className="action delete">
                      <Popconfirm
                        title="Xoá Size"
                        description="Bạn có chắc chắn muốn xoá size này?"
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
  );
};

export { SizeList };
