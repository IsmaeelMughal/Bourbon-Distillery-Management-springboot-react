import React from "react";
import { ToastContainer, toast } from "react-toastify";
import { useNavigate, useLocation } from "react-router-dom";
import Typography from "@mui/material/Typography";
import { editBourbonDistillery } from "../../services/BourbonDistilleryAPI";

function EditDistillery() {
  const location = useLocation();
  const distilleryData = location.state.distilleryData;

  const navigate = useNavigate();
  const handleSubmitDistillery = async (event) => {
    event.preventDefault();
    const requestBody = {
      distilleryId: distilleryData.distilleryId,
      name: event.target.name.value,
      licenseNumber: event.target.licenseNumber.value,
      address: event.target.address.value,
    };
    try {
      const res = await editBourbonDistillery(requestBody);
      if (res.statusCode === 200) {
        toast.success("Bourbon Distillery Edited Successfull!!!");
        toast.success("You will be directed to home page in few seconds!!!");
        setTimeout(() => {
          navigate("/");
        }, 5000);
      } else {
        toast.error(res.errorMessage);
      }
    } catch {
      toast.error("Failed to Edit Bourbon Distillery!!!");
    }
  };
  return (
    <div className="container my-5">
      <ToastContainer />
      <Typography variant="h4" gutterBottom>
        Edit New Bourbon Distillery
      </Typography>
      <form onSubmit={handleSubmitDistillery}>
        <div className="mb-3">
          <label htmlFor="name" className="form-label">
            Name
          </label>
          <input
            type="text"
            className="form-control"
            id="name"
            name="name"
            defaultValue={distilleryData.name}
            aria-describedby="name"
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="licenseNumber" className="form-label">
            License Number
          </label>
          <input
            type="text"
            className="form-control"
            id="licenseNumber"
            name="licenseNumber"
            defaultValue={distilleryData.licenseNumber}
            aria-describedby="licenseNumber"
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="address" className="form-label">
            Address
          </label>
          <input
            type="text"
            defaultValue={distilleryData.address}
            className="form-control"
            id="address"
            name="address"
            aria-describedby="address"
            required
          />
        </div>

        <button type="submit" className="btn btn-primary">
          Edit Distillery
        </button>
      </form>
    </div>
  );
}

export default EditDistillery;
