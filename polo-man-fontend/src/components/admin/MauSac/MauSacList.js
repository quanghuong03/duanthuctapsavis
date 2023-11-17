import { useEffect, useState } from "react";
import {  mauSacService } from "../../../service/admin";
import "../ChatLieu/ChatLieuList.css";
import { Button, Popconfirm } from "antd";
import { Link } from "react-router-dom";

const MauSacList = () => {
  const [mauSac, setMauSac] = useState([]);
  useEffect(() => {
    (async () => {
      const body = await mauSacService.getAllMauSac();
      console.log(body.data);
      setMauSac(body.data);
    })();
  }, []);
  return (
    <div>
      <Link to={"/admin/mausac/add"}>
        <Button type="primary">Thêm màu sắc</Button>
      </Link>
      <br></br>
      <br></br>
      <table className="table">
        <thead>
          <tr>
            <th>Id</th>
            <th>Tên</th>

            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {mauSac.map((material) => {
            return (
              <tr key={material.mamausac}>
                <td>{material.mamausac}</td>
                <td>{material.tenmau}</td>

                <td>
                  <div className="actions">
                    <div className="action update">
                      <Link
                        to={`/admin/mausac/update/${material.mamausac}`}
                      >
                        <Button type="primary" className="btn">
                          <i className="fa-regular fa-pen-to-square"></i>
                        </Button>
                      </Link>
                    </div>
                    <div className="action delete">
                      <Popconfirm
                        title="Xoá màu sắc"
                        description="Bạn có chắc chắn muốn xoá màu sắc này?"
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

export { MauSacList };
