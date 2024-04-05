import React from "react";
import { ToastContainer, toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import Typography from "@mui/material/Typography";
import { addCustomer } from "../../services/CustomerAPI";

function AddCustomer() {
  const navigate = useNavigate();

  const handleSubmitCustomer = async (event) => {
    event.preventDefault();
    const requestBody = {
      name: event.target.name.value,
      phoneNumber: event.target.phoneNumber.value,
    };
    try {
      const res = await addCustomer(requestBody);
      if (res.statusCode === 201) {
        toast.success("Customer Added Successfull!!!");
        toast.success(
          "You will be directed to Customers page in few seconds!!!"
        );
        setTimeout(() => {
          navigate("/customers");
        }, 5000);
      } else {
        toast.error(res.errorMessage);
      }
    } catch {
      toast.error("Failed to Add Customer!!!");
    }
  };
  return (
    <div className="container my-5">
      <ToastContainer />
      <Typography variant="h4" gutterBottom>
        Add New Customer
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
            name="phoneNumber"
            aria-describedby="phoneNumber"
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">
          Add Customer
        </button>
      </form>
    </div>
  );
}

export default AddCustomer;
