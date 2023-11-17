import { useEffect, useState } from "react";
import { DongspService } from "../../../service/admin";
import "../ChatLieu/ChatLieuList.css";
import { Button, Popconfirm } from "antd";
import { Link } from "react-router-dom";

const DongSPList = () => {
  const [dongsp, setDongSp] = useState([]);
  useEffect(() => {
    (async () => {
      const body = await DongspService.getdongsp();
      console.log(body.data);
      setDongSp(body.data);
    })();
  }, []);
  return (
    <div>
      <Link to={"/admin/dongsp/add"}>
        <Button type="primary">Thêm Dòng sản phẩm</Button>
      </Link>
      <br></br>
      <br></br>
      <table className="table">
        <thead>
          <tr>
            <th>Id</th>
            <th>Teen dòng sản phẩm</th>

            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {dongsp.map((material) => {
            return (
              <tr key={material.madongsp}>
                <td>{material.madongsp}</td>
                <td>{material.tendongsp}</td>

                <td>
                  <div className="actions">
                    <div className="action update">
                      <Link to={`/admin/dongsp/update/${material.madongsp}`}>
                        <Button type="primary" className="btn">
                          <i className="fa-regular fa-pen-to-square"></i>
                        </Button>
                      </Link>
                    </div>
                    <div className="action delete">
                      <Popconfirm
                        title="Xoá Dòng sản phẩm"
                        description="Bạn có chắc chắn muốn xoá dòng sản phẩm này?"
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

export { DongSPList };
