import React, { useState, useEffect } from "react";
import { ToastContainer, toast } from "react-toastify";
import {
  deleteBourbonDistillery,
  getAllBourbonDistilleries,
} from "../../services/BourbonDistilleryAPI";
import DistilleryCard from "../../components/DistilleryCard";
import ComponentLoader from "../../components/ComponentLoader";

function AllDistelleries() {
  const [listOfBourbonDistilleries, setListOfBourbonDistilleries] = useState(
    []
  );

  const [isLoading, setIsLoading] = useState(false);

  const fetchAllBourbonDistilleries = async () => {
    try {
      setIsLoading(true);
      const res = await getAllBourbonDistilleries();
      if (res.statusCode === 200) {
        setListOfBourbonDistilleries(res.data);
      } else {
        toast.error("Failed To Fetch Bourbon Distilleries");
      }
    } catch (err) {
      toast.error("Failed To Fetch Bourbon Distilleries");
    } finally {
      setIsLoading(false);
    }
  };

  const handleDeleteBourbonDistillery = async (distilleryId) => {
    try {
      const res = await deleteBourbonDistillery(distilleryId);
      if (res.statusCode === 200) {
        const remDistilleries = listOfBourbonDistilleries.filter(
          (distillery) => distillery.distilleryId !== distilleryId
        );
        setListOfBourbonDistilleries(remDistilleries);
        toast.success("Bourbon Distillery Deleted Successfully!!!");
      } else {
        toast.error(res.errorMessage);
      }
    } catch (error) {
      toast.error("Failed to Delete Bourbon Distillery!!!");
    }
  };

  useEffect(() => {
    fetchAllBourbonDistilleries();
  }, []);

  return (
    <div>
      <ToastContainer />
      <div className="container px-4 px-lg-5">
        <div className="row gx-4 gx-lg-5 justify-content-center">
          <h1 className="text-center">All Bourbon Distilleries</h1>
          {isLoading ? (
            <ComponentLoader />
          ) : (
            <div className="col-md-10 col-lg-8 col-xl-7">
              {listOfBourbonDistilleries && listOfBourbonDistilleries.length > 0
                ? listOfBourbonDistilleries.map((bourbonDistillery) => {
                    return (
                      <DistilleryCard
                        distilleryData={bourbonDistillery}
                        handleDeleteBourbonDistillery={
                          handleDeleteBourbonDistillery
                        }
                        key={bourbonDistillery.distilleryId}
                      />
                    );
                  })
                : "NO DISTILLERY FOUND"}
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default AllDistelleries;
