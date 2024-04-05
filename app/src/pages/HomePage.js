import React, { useState, useEffect } from "react";
import { ToastContainer, toast } from "react-toastify";
import { getAllBourbonDistilleries } from "../services/BourbonDistilleryAPI";

function HomePage() {
  const [listOfBourbonDistilleries, setListOfBourbonDistilleries] = useState(
    []
  );

  const fetchAllBourbonDistilleries = async () => {
    try {
      const res = await getAllBourbonDistilleries();
      if (res.statusCode === 200) {
        setListOfBourbonDistilleries(res.data);
      } else {
        toast("Failed To Fetch Bourbon Distilleries");
      }
    } catch (err) {
      toast("Failed To Fetch Bourbon Distilleries");
    }
  };

  useEffect(() => {
    fetchAllBourbonDistilleries();
  }, []);

  return (
    <div>
      <ToastContainer />
    </div>
  );
}

export default HomePage;
