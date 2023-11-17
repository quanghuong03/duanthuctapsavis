import { Button, Form, Image, Input, Space, Upload } from "antd";
import { useEffect, useState } from "react";
import { storage } from "../../../../firebaseConfig";
import {
  chatLieuSerivce,
  sanphamService,
  DongspService,
  thuonghieuService,
  sizeService,
  mauSacService,
} from "../../../../service/admin";
import { selectSearchDataUtil } from "../../../../utils";
import { SelectSearch } from "../../../common/SelectSearch";
// import { AddBrandModal, AddCategoryModal } from "../../../common";
import ReactQuill from "react-quill";
import "./AddBrand.css";
// import { AddMaterialModal } from "../../../common/Modal/AddMaterialModal";
import { Typography } from "antd";
import {
  MinusCircleOutlined,
  PlusOutlined,
  UploadOutlined,
} from "@ant-design/icons";
import { fileService, toastService } from "../../../../service/common";
import { v4 as uuid } from "uuid";
import { useNavigate, useParams } from "react-router-dom";
// import { AddDiscountModal } from "../../../common/Modal";

const { Title } = Typography;

const AddProduct = () => {
  const [categoryOptions, setCategoryOptions] = useState([]);
  const [brandOptions, setBrandOptions] = useState([]);
  const [materialOptions, setMaterialOptions] = useState([]);
  const [sizeOptions, setSizeOptions] = useState([]);
  const [colorOptions, setColorOptions] = useState([]);
  const [discountOptions, setDiscountOptions] = useState([]);

  const [showAddCategoryModal, setShowAddCategoryModal] = useState(false);
  const [showAddBrandModal, setShowAddBrandModal] = useState(false);
  const [showMaterialModal, setShowAddMaterialModal] = useState(false);
  const [showAddDiscountModal, setShowAddDiscountModal] = useState(false);

  const { masanpham } = useParams();

  const [images, setImages] = useState({});
  const [selectedImage, setSelectedImage] = useState(null);
  const [fileName, setFileName] = useState("");
  const navigate = useNavigate();
  const [hinhanh, setHinhAnh] = useState("");
  const [file, setFile] = useState(null);
  const [form] = Form.useForm();
  const [selectedImageUrl, setSelectedImageUrl] = useState(null);
  useEffect(() => {
    (async () => {
      try {
        const categoryRes = await DongspService.getdongsp();
        const brandRes = await thuonghieuService.getThuongHieu();
        const materialRes = await chatLieuSerivce.getChatLieu();
        const sizeRes = await sizeService.getAllSize();
        const colorRes = await mauSacService.getAllMauSac();

        const materialOptions = selectSearchDataUtil.transformSearchSelectData(
          materialRes.data,
          "machatlieu",
          "tenchatlieu"
        );
        const categoryOptions = selectSearchDataUtil.transformSearchSelectData(
          categoryRes.data,
          "madongsp",
          "tendongsp"
        );
        const brandOptions = selectSearchDataUtil.transformSearchSelectData(
          brandRes.data,
          "mathuonghieu",
          "tenthuonghieu"
        );
        const sizeOptions = selectSearchDataUtil.transformSearchSelectData(
          sizeRes.data,
          "masize",
          "sosize"
        );
        const colorOptions = selectSearchDataUtil.transformSearchSelectData(
          colorRes.data,
          "mamausac",
          "tenmau"
        );

        setMaterialOptions(materialOptions);
        setCategoryOptions(categoryOptions);
        setBrandOptions(brandOptions);
        setSizeOptions(sizeOptions);
        setColorOptions(colorOptions);
        setDiscountOptions(discountOptions);

        if (masanpham) {
          const { data } = await sanphamService.getProductById(masanpham);

          const {
            tensanpham,
            madongsp,
            gianhap,
            giaban,
            mota,
            mathuonghieu,
            machatlieu,
            hinhanh,
            list,
          } = data;
          setFileName(hinhanh);
          setHinhAnh(hinhanh);
          const sanPhamChiTietRequests = list.map((product_detail) => {
            return {
              ...product_detail,
            };
          });

          console.log(sanPhamChiTietRequests); // Log the list of product details
          const formValue = {
            tensanpham,
            madongsp,
            gianhap,
            giaban,
            mota,
            mathuonghieu,
            machatlieu,
            hinhanh: fileName,
            sanPhamChiTietRequests,
          };

          form.setFieldsValue(formValue);
          console.log("Form values:", form.getFieldsValue()); // Log the form values
        }
      } catch (error) {
        console.log(error);
        toastService.error(error.apiMessage || "Server error");
      }
    })();
  }, []);

  const addProductHandle = async (e) => {
    if (file) {
      const storageRef = storage.ref();
      const imageRef = storageRef.child(`images/${file.name}`);

      try {
        await imageRef.put(file);
        console.log("Image uploaded successfully!");

        const url = await imageRef.getDownloadURL();
        console.log("Image URL:", url);

        // Update the form value with the image URL
        e.hinhanh = url;
      } catch (error) {
        console.error("Error uploading image:", error);
      }
    }
    if (masanpham) {
      const formValue = form.getFieldsValue();

      const productDetails = formValue.sanPhamChiTietRequests.map(
        (productDetail) => {
          const { form_id } = productDetail;
          const image = images[form_id];
          if (image) {
            return {
              ...productDetail,
              hinhanh: image,
            };
          }
          return productDetail;
        }
      );
      formValue.sanPhamChiTietRequests = productDetails;

      console.log(productDetails);
      formValue.hinhanh = e.hinhanh; // Preserve the existing image URL
      await sanphamService.updateProductById(masanpham, formValue);
      navigate("/admin/sanpham");
      toastService.success("Cập nhật sản phẩm thành công");
      return;
    }
    const request = {
      ...e,
    };

    await sanphamService.createProduct(request);
    toastService.success("Tạo sản phẩm thành công");
    navigate("/admin/sanpham");
  };

  async function createCategoryFinishHandle(newCategory) {
    setShowAddCategoryModal(false);
    setCategoryOptions((pre) => {
      return [
        {
          value: newCategory.madongsp,
          label: newCategory.tendongsp,
        },
        ...pre,
      ];
    });
    form.setFieldValue("madongsp", newCategory.id);
  }

  async function createBrandFinishHandle(newBrand) {
    setShowAddBrandModal(false);
    setBrandOptions((pre) => {
      return [
        {
          value: newBrand.mathuonghieu,
          label: newBrand.tenthuonghieu,
        },
        ...pre,
      ];
    });
    form.setFieldValue("mathuonghieu", newBrand.mathuonghieu);
  }

  async function createMaterialFinishHandle(newMaterial) {
    setShowAddMaterialModal(false);
    setMaterialOptions((pre) => {
      return [
        {
          value: newMaterial.machatlieu,
          label: newMaterial.tenchatlieu,
        },
        ...pre,
      ];
    });
    form.setFieldValue("machatlieu", newMaterial.machatlieu);
  }

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    setSelectedImageUrl(URL.createObjectURL(file));
    setFile(file);
  };

  async function deleteProductDetailHandle(form_id, remove, name) {
    const formValue = form.getFieldsValue();
    const productDetail = formValue.sanPhamChiTietRequests[form_id];
    if (!productDetail?.product_detail_id) {
      remove(name);
      return;
    }
    const machitietsanpham = productDetail.machitietsanpham;

    // await productDetailService.deleteProductDetailById(product_detail_id);
    form.setFieldsValue({
      ...formValue,
      sanPhamChiTietRequests: formValue.sanPhamChiTietRequests.filter(
        (p) => p.machitietsanpham !== machitietsanpham
      ),
    });
    toastService.success("Xóa phân loại sản phẩm thành công");
  }

  return (
    <div>
      <Title level={1}>
        {" "}
        {masanpham ? "Cập nhật sản phẩm" : "Thêm sản phẩm"}
      </Title>
      {/* <AddCategoryModal
        open={showAddCategoryModal}
        onCreateCategoryFinish={createCategoryFinishHandle}
        onCancel={() => setShowAddCategoryModal(false)}
      />
      <AddBrandModal
        open={showAddBrandModal}
        onAddBrandFinish={createBrandFinishHandle}
        onCancel={() => setShowAddBrandModal(false)}
      /> */}
      {/* <AddMaterialModal
        onAddMaterialFinish={createMaterialFinishHandle}
        open={showMaterialModal}
        onCancel={() => setShowAddMaterialModal(false)}
      />

      <AddDiscountModal
        onAddDiscountFinish={createDiscountFinishHandle}
        open={showAddDiscountModal}
        onCancel={() => setShowAddDiscountModal(false)}
      /> */}
      <Form onFinish={addProductHandle} form={form}>
        <div style={{ maxWidth: "800px" }} className="main_add_product_form">
          <Form.Item
            name={"tensanpham"}
            label="Tên sản phẩm"
            rules={[{ required: true, message: "Vui lòng nhập tên sản phẩm" }]}
          >
            <Input />
          </Form.Item>

          <div
            style={{
              display: "flex",
            }}
          >
            <Form.Item
              style={{
                width: "95%",
              }}
              name="madongsp"
              label="Danh mục"
              rules={[
                {
                  required: true,
                  message: "Vui lòng chọn danh mục",
                },
              ]}
            >
              <SelectSearch options={categoryOptions} />
            </Form.Item>
            <Button onClick={() => setShowAddCategoryModal(true)}>
              <i className="fa-solid fa-plus"></i>
            </Button>
          </div>

          <div
            style={{
              display: "flex",
            }}
          >
            <Form.Item
              style={{
                width: "95%",
              }}
              name="mathuonghieu"
              label="Thương hiệu"
              rules={[
                {
                  required: true,
                  message: "Vui lòng chọn thương hiệu",
                },
              ]}
            >
              <SelectSearch options={brandOptions} />
            </Form.Item>
            <Button onClick={() => setShowAddBrandModal(true)}>
              <i className="fa-solid fa-plus"></i>
            </Button>
          </div>

          <div
            style={{
              display: "flex",
            }}
          >
            <Form.Item
              style={{
                width: "95%",
              }}
              name="machatlieu"
              label="Chất liệu"
              rules={[
                {
                  required: true,
                  message: "Vui lòng chọn chất liệu",
                },
              ]}
            >
              <SelectSearch options={materialOptions} />
            </Form.Item>
            <Button onClick={() => setShowAddMaterialModal(true)}>
              <i className="fa-solid fa-plus"></i>
            </Button>
          </div>

          <Form.Item
            name="gianhap"
            label="Giá nhập"
            rules={[
              {
                required: true,
                message: "Vui lòng nhập giá",
              },
            ]}
          >
            <Input type={"number"} min={0} disabled={masanpham} />
          </Form.Item>

          <Form.Item
            name="giaban"
            label="Giá bán"
            rules={[
              {
                required: true,
                message: "Vui lòng nhập giá",
              },
            ]}
          >
            <Input type={"number"} min={0} />
          </Form.Item>

          <Form.Item
            name={"mota"}
            label="Mô tả sản phẩm"
            rules={[{ required: true, message: "Vui Lòng nhập mô ta" }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            name={"hinhanh"}
            label="Ảnh sản phẩm"
            rules={[{ required: true, message: "Vui lòng chọn ảnh sản phẩm" }]}
          >
            <div>
              {hinhanh && typeof hinhanh === "string" && (
                <img
                  src={hinhanh}
                  alt="Existing"
                  style={{ width: "200px", height: "200px" }}
                />
              )}
              <input type="file" accept="image/*" onChange={handleFileChange} />
              {selectedImageUrl && (
                <img
                  src={selectedImageUrl}
                  alt="Selected"
                  style={{ width: "200px", height: "200px" }}
                />
              )}
            </div>
          </Form.Item>
        </div>

        <div className="product-details-form">
          <Title level={3}>Phân loại sản phẩm</Title>

          <div className="product-details" style={{ maxWidth: "800px" }}>
            <Form.List name="sanPhamChiTietRequests">
              {(fields, { add, remove }) => (
                <>
                  {fields.map(({ key, name, ...restField }) => (
                    <div key={key}>
                      <Space
                        key={key}
                        style={{ display: "flex", marginBottom: 8 }}
                        align="baseline"
                      >
                        <Form.Item
                          {...restField}
                          name={[name, "form_id"]}
                          initialValue={key}
                        >
                          <Input type="hidden" />
                        </Form.Item>
                        <Form.Item
                          {...restField}
                          name={[name, "masize"]}
                          rules={[
                            {
                              required: true,
                              message: "Vui lòng chọn màu sản phẩm",
                            },
                          ]}
                        >
                          <SelectSearch
                            options={sizeOptions}
                            placeholder="size"
                          />
                        </Form.Item>
                        <Form.Item
                          {...restField}
                          name={[name, "mamausac"]}
                          rules={[
                            {
                              required: true,
                              message: "Vui lòng chọn size sản phẩm",
                            },
                          ]}
                        >
                          <SelectSearch
                            options={colorOptions}
                            placeholder="color"
                          />
                        </Form.Item>
                        <Form.Item
                          {...restField}
                          name={[name, "soluongton"]}
                          rules={[
                            {
                              required: true,
                              message: "Vui lòng nhập số lượng phân loại",
                            },
                          ]}
                        >
                          <Input placeholder="Số lượng" />
                        </Form.Item>

                        <MinusCircleOutlined
                          onClick={() =>
                            deleteProductDetailHandle(key, remove, name)
                          }
                        />
                      </Space>
                    </div>
                  ))}
                  <Form.Item>
                    <Button
                      type="dashed"
                      onClick={() => add()}
                      block
                      icon={<PlusOutlined />}
                    >
                      Add field
                    </Button>
                  </Form.Item>
                </>
              )}
            </Form.List>
          </div>
        </div>

        <Button className="mt-3" htmlType="submit" type="primary">
          {masanpham ? "Cập nhật sản phẩm" : "Thêm sản phẩm"}
        </Button>
      </Form>
    </div>
  );
};

export { AddProduct };
