import React, { useEffect } from "react";
import UserHeader from "./UserHeader";
import { Outlet, useNavigate } from "react-router-dom";

function UserLayout() {
  const navigate = useNavigate();
  useEffect(() => {
    const token = JSON.parse(localStorage.getItem("refreshToken"));
    if (!token || token === "") {
      navigate("/");
    }
  }, []);

  return (
    <div>
      <UserHeader />
      <Outlet />
    </div>
  );
}

export default UserLayout;
