import React from "react";
import { Navigate, Route, Routes } from "react-router-dom";
import PageNotFound from "./pages/PageNotFound";
import UserLayout from "./layout/UserLayout";
import HomePage from "./pages/HomePage";

const App = () => {
  return (
    <Routes>
      <Route path="/" element={<UserLayout />}>
        <Route index element={<HomePage />} />
        {/* <Route path="addtask" element={<AddTask />} /> */}
      </Route>

      <Route path="/404" element={<PageNotFound />} />
      <Route path="*" element={<Navigate to={"/404"} />} />
    </Routes>
  );
};

export default App;
