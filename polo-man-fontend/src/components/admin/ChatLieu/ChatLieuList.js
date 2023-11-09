import { useEffect, useState } from "react";
import { chatLieuSerivce } from "../../../service/admin";
import "./ChatLieuList.css";
import { Button, Popconfirm } from "antd";
import { Link } from "react-router-dom";

const ChatLieuList = () => {
  const [chatLieu, setChatLieu] = useState([]);
  useEffect(() => {
    (async () => {
      const body = await chatLieuSerivce.getChatLieu();
      console.log(body.data);
      setChatLieu(body.data);
    })();
  }, []);
  return (
    <div>
      <Link to={"/admin/chatlieu/add"}>
        <Button type="primary">Thêm chất liệu sản phẩm</Button>
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
          {chatLieu.map((material) => {
            return (
              <tr key={material.machatlieu}>
                <td>{material.machatlieu}</td>
                <td>{material.tenchatlieu}</td>

                <td>
                  <div className="actions">
                    <div className="action update">
                      <Link
                        to={`/admin/chatlieu/update/${material.machatlieu}`}
                      >
                        <Button type="primary" className="btn">
                          <i className="fa-regular fa-pen-to-square"></i>
                        </Button>
                      </Link>
                    </div>
                    <div className="action delete">
                      <Popconfirm
                        title="Xoá chất liệu"
                        description="Bạn có chắc chắn muốn xoá chất liệu này?"
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

export { ChatLieuList };
