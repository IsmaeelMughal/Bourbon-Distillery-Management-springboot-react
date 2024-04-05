import React from "react";
import "./App.css";
import "react-toastify/dist/ReactToastify.css";

import { Navigate, Route, Routes } from "react-router-dom";
import PageNotFound from "./pages/PageNotFound";
import UserLayout from "./layout/UserLayout";
import AllDistelleries from "./pages/BourbonDistillery/AllDistelleries";
import AddDistillery from "./pages/BourbonDistillery/AddDistillery";
import EditDistillery from "./pages/BourbonDistillery/EditDistillery";
import AllCustomers from "./pages/Customer/AllCustomers";
import AddCustomer from "./pages/Customer/AddCustomer";
import EditCustomer from "./pages/Customer/EditCustomer";

const App = () => {
  return (
    <Routes>
      <Route path="/" element={<UserLayout />}>
        <Route index element={<AllDistelleries />} />
        <Route path="add-distillery" element={<AddDistillery />} />
        <Route path="edit-distillery" element={<EditDistillery />} />
        <Route path="customers" element={<AllCustomers />} />
        <Route path="add-customer" element={<AddCustomer />} />
        <Route path="edit-customer" element={<EditCustomer />} />
      </Route>

      <Route path="/404" element={<PageNotFound />} />
      <Route path="*" element={<Navigate to={"/404"} />} />
    </Routes>
  );
};

export default App;
