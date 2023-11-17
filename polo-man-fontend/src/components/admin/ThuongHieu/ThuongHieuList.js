import { useEffect, useState } from "react";
import { thuonghieuService } from "../../../service/admin";
import "../ChatLieu/ChatLieuList.css";
import { Button, Popconfirm } from "antd";
import { Link } from "react-router-dom";

const ThuongHieuList = () => {
  const [thuonghieu, setThuonghieu] = useState([]);
  useEffect(() => {
    (async () => {
      const body = await thuonghieuService.getThuongHieu();
      console.log(body.data);
      setThuonghieu(body.data);
    })();
  }, []);
  return (
    <div>
      <Link to={"/admin/thuonghieu/add"}>
        <Button type="primary">Thêm Thương hiệu</Button>
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
          {thuonghieu.map((material) => {
            return (
              <tr key={material.mathuonghieu}>
                <td>{material.mathuonghieu}</td>
                    <td>{material.tenthuonghieu}</td>
                   

                <td>
                  <div className="actions">
                    <div className="action update">
                      <Link
                        to={`/admin/thuonghieu/update/${material.mathuonghieu}`}
                      >
                        <Button type="primary" className="btn">
                          <i className="fa-regular fa-pen-to-square"></i>
                        </Button>
                      </Link>
                    </div>
                    <div className="action delete">
                      <Popconfirm
                        title="Xoá Thương hiệu"
                        description="Bạn có chắc chắn muốn xoá thương hiệu này?"
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

export { ThuongHieuList };
