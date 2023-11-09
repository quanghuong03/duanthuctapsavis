import { Breadcrumb } from "antd";
import { Link, useLocation } from "react-router-dom";

const breadcrumbNameMap = {
  "/admin": "Dashboard",
  "/admin/chatlieu": "ChatLieu List",
  "/admin/chatlieu/add": "Add Chất Liệu",
};

const AdminBreadCrumb = () => {
  const location = useLocation();
  const pathSnippets = location.pathname.split("/").filter((i) => i);

  const extraBreadcrumbItems = pathSnippets.map((_, index) => {
    const url = `/${pathSnippets.slice(0, index + 1).join("/")}`;
    return {
      key: url,
      title: <Link to={url}>{breadcrumbNameMap[url]}</Link>,
    };
  });

  return <Breadcrumb items={extraBreadcrumbItems} />;
};

export { AdminBreadCrumb };
