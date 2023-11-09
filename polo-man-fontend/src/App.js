import logo from "./logo.svg";
import "./App.css";

import { UserLayout, AdminLayout } from "./layout";
import { DatePicker } from "antd";
import { AdminLogin } from "./layout/admin";
import { Route, Router, Routes } from "react-router-dom";

import { adminRoutes, privateRoutes } from "./routes";
import { useEffect } from "react";
import { Cloudinary } from "@cloudinary/url-gen";
import axios from "axios";

const DefaultLauyout = ({ children }) => {
  return <>{children}</>;
};

const NoGuard = ({ children }) => {
  return <>{children}</>;
};

function App() {
  return (
    <Routes>
      {adminRoutes.map((route, index) => {
        const Page = route.component;
        const Layout = route.layout || DefaultLauyout;
        let Guard = route.guard || NoGuard;

        return (
          <Route
            key={index}
            path={route.path}
            element={
              <Guard>
                <Layout>
                  <Page />
                </Layout>
              </Guard>
            }
          ></Route>
        );
      })}
    </Routes>
  );
}

export default App;
