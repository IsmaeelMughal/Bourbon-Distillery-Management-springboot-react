import React, { useState } from "react";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import Button from "@mui/material/Button";
import { useNavigate } from "react-router-dom";

function DistilleryCard({ distilleryData, handleDeleteBourbonDistillery }) {
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
    await handleDeleteBourbonDistillery(distilleryData.distilleryId);
  };
  const handleEditDistilleryClick = () => {
    navigate("/edit-distillery", {
      state: { distilleryData: distilleryData },
    });
  };

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
            className="btn btn-info mx-2"
            onClick={handleEditDistilleryClick}
          >
            Show Details
          </button>
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

      <Dialog
        open={openDeleteModal}
        onClose={handleCloseDeleteModal}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">Delete Distillery</DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            Are you sure you want to delete this Distillery?
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

export default DistilleryCard;
