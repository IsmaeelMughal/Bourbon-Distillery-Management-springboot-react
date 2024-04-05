import React from "react";
import { ToastContainer, toast } from "react-toastify";
import { useNavigate, useLocation } from "react-router-dom";
import Typography from "@mui/material/Typography";

function DetailDistillery() {
  const location = useLocation();
  const distilleryData = location.state.distilleryData;

  return (
    <div>
      <div className="card my-5">
        <div className="card-body text-center">
          <h5 className="card-title">{distilleryData.name}</h5>
          <p className="card-text">{distilleryData.address}</p>
          <div>
            <p className="card-text">
              <span className="fw-bold">Licence #:</span>{" "}
              {distilleryData.licenseNumber ? distilleryData.licenseNumber : ""}
            </p>
          </div>
          <button
            className="btn btn-secondary mx-2"
            onClick={handleEditDistilleryClick}
          >
            Edit
          </button>
          <button
            className="btn btn-danger mx-2"
            onClick={handleOpenDeleteModal}
          >
            Delete
          </button>
        </div>
      </div>
    </div>
  );
}

export default DetailDistillery;
