import React from "react";
import { ToastContainer, toast } from "react-toastify";
import { useNavigate, useLocation } from "react-router-dom";
import Typography from "@mui/material/Typography";
import { editCustomer } from "../../services/CustomerAPI";

function EditCustomer() {
  const location = useLocation();
  const customerData = location.state.customerData;

  const navigate = useNavigate();
  const handleSubmitCustomer = async (event) => {
    event.preventDefault();
    const requestBody = {
      customerId: customerData.customerId,
      name: event.target.name.value,
      phoneNumber: event.target.phoneNumber.value,
    };
    try {
      const res = await editCustomer(requestBody);
      if (res.statusCode === 200) {
        toast.success("Cutomer Edited Successfull!!!");
        toast.success(
          "You will be directed to Customer page in few seconds!!!"
        );
        setTimeout(() => {
          navigate("/customers");
        }, 5000);
      } else {
        toast.error(res.errorMessage);
      }
    } catch {
      toast.error("Failed to Edit Customer!!!");
    }
  };

  return (
    <div className="container my-5">
      <ToastContainer />
      <Typography variant="h4" gutterBottom>
        Edit Customer
      </Typography>
      <form onSubmit={handleSubmitCustomer}>
        <div className="mb-3">
          <label htmlFor="name" className="form-label">
            Name
          </label>
          <input
            type="text"
            className="form-control"
            id="name"
            name="name"
            aria-describedby="name"
            required
            defaultValue={customerData.name}
          />
        </div>
        <div className="mb-3">
          <label htmlFor="phoneNumber" className="form-label">
            Phone Number
          </label>
          <input
            type="text"
            className="form-control"
            id="phoneNumber"
            defaultValue={customerData.phoneNumber}
            name="phoneNumber"
            aria-describedby="phoneNumber"
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">
          Edit Customer
        </button>
      </form>
    </div>
  );
}

export default EditCustomer;
