import React, { useState } from "react";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import Button from "@mui/material/Button";
import { useNavigate } from "react-router-dom";

function CustomerCard({ customerData, handleDeleteCustomer }) {
  const navigate = useNavigate();

  const [openDeleteModal, setOpenDeleteModal] = useState(false);
  const handleOpenDeleteModal = () => {
    setOpenDeleteModal(true);
  };
  const handleCloseDeleteModal = () => {
    setOpenDeleteModal(false);
  };
  const handleDeleteClick = async () => {
    setOpenDeleteModal(false);
    await handleDeleteCustomer(customerData.customerId);
  };
  const handleEditCustomerClick = () => {
    navigate("/edit-customer", {
      state: { customerData: customerData },
    });
  };
  return (
    <div>
      <div className="card my-5">
        <div className="card-body text-center">
          <h5 className="card-title">{customerData.name}</h5>
          <p className="card-text">{customerData.phoneNumber}</p>
          <button
            className="btn btn-secondary mx-2"
            onClick={handleEditCustomerClick}
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

      <Dialog
        open={openDeleteModal}
        onClose={handleCloseDeleteModal}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">Delete Customer</DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            Are you sure you want to delete this Customer?
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleDeleteClick}>Yes</Button>
          <Button onClick={handleCloseDeleteModal} autoFocus>
            No
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}

export default CustomerCard;
