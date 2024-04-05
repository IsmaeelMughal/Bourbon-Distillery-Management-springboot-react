import React, { useState, useEffect } from "react";
import { ToastContainer, toast } from "react-toastify";
import { useLocation } from "react-router-dom";
import { getDistilleryDetails } from "../../services/BourbonDistilleryAPI";
import Table from "@mui/material/Table";
import Select from "react-select";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import Button from "@mui/material/Button";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import ComponentLoader from "../../components/ComponentLoader";
import { addBourbon, deleteBourbon } from "../../services/BourbonAPI";
import {
  assignCustomerToDistillery,
  getUnassignedCustomers,
  unAssignCustomerFromDistillery,
} from "../../services/CustomerAPI";

const bourbonTypeOptions = [
  { value: "SINGLE_BARREL", label: "Single Barrel" },
  { value: "CASK_STRENGTH", label: "Cask Strength" },
  { value: "WHEATED", label: "Wheated" },
  { value: "HIGH_RYE", label: "High Rye" },
  { value: "HIGH_CORN", label: "High Corn" },
  { value: "SMALL_BATCH", label: "Small Batch" },
];

function DetailDistillery() {
  const location = useLocation();
  const distilleryData = location.state.distilleryData;

  const [isLoading, setIsLoading] = useState(false);
  const [listOfBourbon, setListOfBourbon] = useState([]);
  const [listOfCustomer, setListOfCustomer] = useState([]);
  const [selectedBourbonTypeOption, setSelectedBourbonTypeOption] =
    useState(null);
  const [openDeleteModal, setOpenDeleteModal] = useState(false);
  const [openDeleteCustomerModal, setOpenDeleteCustomerModal] = useState(false);

  const [selectedBourbon, setSelectedBourbon] = useState(null);
  const [selectedCustomer, setSelectedCustomer] = useState(null);

  const [selectedCustomerOption, setSelectedCustomerOption] = useState(null);
  const [customerOptions, setCustomerOptions] = useState([]);

  const handleCustomerOptionChange = (selectedOption) => {
    setSelectedCustomerOption(selectedOption);
  };

  const handleOpenDeleteModal = () => {
    setOpenDeleteModal(true);
  };
  const handleCloseDeleteModal = () => {
    setOpenDeleteModal(false);
  };

  const handleOpenDeleteCustomerModal = () => {
    setOpenDeleteCustomerModal(true);
  };
  const handleCloseDeleteCustomerModal = () => {
    setOpenDeleteCustomerModal(false);
  };

  const handleDeleteClick = async () => {
    setOpenDeleteModal(false);
    try {
      const res = await deleteBourbon(selectedBourbon);
      if (res.statusCode === 200) {
        const remBourbon = listOfBourbon.filter(
          (bourbon) => bourbon.bourbonId !== selectedBourbon
        );
        setListOfBourbon(remBourbon);
        toast.success("Bourbon Deleted Successfully!!!");
      } else {
        toast.error(res.errorMessage);
      }
    } catch (error) {
      toast.error("Failed to Delete Bourbon!!!");
    }
  };
  const handleBourbonTypeChange = (selectedOption) => {
    setSelectedBourbonTypeOption(selectedOption);
  };
  const fetchDistilleryDetails = async () => {
    try {
      setIsLoading(true);
      const res = await getDistilleryDetails(distilleryData.distilleryId);
      if (res.statusCode === 200) {
        setListOfBourbon(res.data.bourbons);
        setListOfCustomer(res.data.customers);
      } else {
        toast.error("Failed To Fetch Distillery Details");
      }
    } catch (err) {
      toast.error("Failed To Fetch Distillery Details");
    } finally {
      setIsLoading(false);
    }
  };

  const fetchUnassignedCustomers = async () => {
    try {
      setIsLoading(true);
      const res = await getUnassignedCustomers(distilleryData.distilleryId);
      if (res.statusCode === 200) {
        const options = res.data.map((customer) => ({
          value: customer.customerId,
          label: customer.name + "-" + customer.phoneNumber,
        }));
        setCustomerOptions(options);
      } else {
        toast.error("Failed To Fetch Distillery Details");
      }
    } catch (err) {
      toast.error("Failed To Fetch Distillery Details");
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    fetchDistilleryDetails();
    fetchUnassignedCustomers();
  }, []);

  const handleAddBourbon = async (event) => {
    event.preventDefault();
    const requestBody = {
      name: event.target.name.value,
      abv: event.target.abv.value,
      type: selectedBourbonTypeOption.value,
      distillery: distilleryData.distilleryId,
    };
    try {
      const res = await addBourbon(requestBody);
      if (res.statusCode === 201) {
        toast.success("Bourbon  Added Successfull!!!");
        setListOfBourbon((prev) => [...prev, res.data]);
      } else {
        toast.error(res.errorMessage);
      }
    } catch {
      toast.error("Failed to Add Bourbon Distillery!!!");
    }
  };

  const handleAssignCustomer = async (event) => {
    event.preventDefault();
    try {
      const res = await assignCustomerToDistillery(
        distilleryData.distilleryId,
        selectedCustomerOption.value
      );
      if (res.statusCode === 200) {
        toast.success("Assigned Customer Successfull!!!");
        setListOfCustomer((prev) => [...prev, res.data]);
        const remOptionsCustomers = customerOptions.filter(
          (customer) => customer.value != selectedCustomerOption.value
        );
        setCustomerOptions(remOptionsCustomers);
        setSelectedCustomerOption(null);
      } else {
        toast.error(res.errorMessage);
      }
    } catch {
      toast.error("Failed to Assign Customer!!!");
    }
  };

  const handleDeleteCustomerClick = async () => {
    try {
      const res = await unAssignCustomerFromDistillery(
        distilleryData.distilleryId,
        selectedCustomer
      );
      if (res.statusCode === 200) {
        toast.success("Customer UnAssigned Successfull!!!");
        const remCustomers = listOfCustomer.filter(
          (customer) => customer.customerId != selectedCustomer
        );
        setListOfCustomer(remCustomers);
        setCustomerOptions((prev) => [
          ...prev,
          {
            value: res.data.customerId,
            label: res.data.name + "-" + res.data.phoneNumber,
          },
        ]);
      } else {
        toast.error(res.errorMessage);
      }
    } catch {
      toast.error("Failed to UnAssign Customer!!!");
    }
  };

  return (
    <div>
      <ToastContainer />
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
        </div>
      </div>

      {!isLoading ? (
        <div>
          <div className="container my-3">
            <h4>Bourbons</h4>
            <form onSubmit={handleAddBourbon}>
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
                <label htmlFor="abv" className="form-label">
                  ABV
                </label>
                <input
                  type="number"
                  className="form-control"
                  id="abv"
                  name="abv"
                  aria-describedby="abv"
                  required
                />
              </div>
              <div>
                <Select
                  value={selectedBourbonTypeOption}
                  onChange={handleBourbonTypeChange}
                  options={bourbonTypeOptions}
                  placeholder="Select a bourbon type"
                  required
                />
              </div>

              <button type="submit" className="btn btn-primary my-2">
                Add Bourbon
              </button>
            </form>
            <TableContainer component={Paper} className="my-5">
              <Table sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                  <TableRow>
                    <TableCell>
                      <h5>Name</h5>
                    </TableCell>
                    <TableCell align="right">
                      <h5>ABV</h5>
                    </TableCell>
                    <TableCell align="right">
                      <h5>Type</h5>
                    </TableCell>
                    <TableCell align="right">
                      <h5>Actions</h5>
                    </TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {listOfBourbon.map((bourbon) => (
                    <TableRow
                      key={bourbon.bourbonId}
                      sx={{
                        "&:last-child td, &:last-child th": {
                          border: 0,
                        },
                      }}
                    >
                      <TableCell component="th" scope="row">
                        {bourbon.name}
                      </TableCell>
                      <TableCell align="right">{bourbon.abv}</TableCell>
                      <TableCell align="right">{bourbon.type}</TableCell>
                      <TableCell align="right">
                        <button
                          className="btn btn-danger mx-2"
                          onClick={() => {
                            handleOpenDeleteModal();
                            setSelectedBourbon(bourbon.bourbonId);
                          }}
                        >
                          Delete
                        </button>
                      </TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          </div>

          <div className="container my-3">
            <h4>Customers</h4>
            <form onSubmit={handleAssignCustomer}>
              <div>
                <p>Select A Customer to Assign to this Distillery:</p>
                <Select
                  value={selectedCustomerOption}
                  onChange={handleCustomerOptionChange}
                  options={customerOptions}
                  placeholder="Select a Customer"
                  required
                />
              </div>
              <button type="submit" className="btn btn-primary my-2">
                Assign Customer
              </button>
            </form>
            <TableContainer component={Paper} className="my-5">
              <Table sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                  <TableRow>
                    <TableCell>
                      <h5>Name</h5>
                    </TableCell>
                    <TableCell align="right">
                      <h5>Phone Number</h5>
                    </TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {listOfCustomer.map((customer) => (
                    <TableRow
                      key={customer.customerId}
                      sx={{
                        "&:last-child td, &:last-child th": {
                          border: 0,
                        },
                      }}
                    >
                      <TableCell component="th" scope="row">
                        {customer.name}
                      </TableCell>
                      <TableCell align="right">
                        {customer.phoneNumber}
                      </TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          </div>
        </div>
      ) : (
        <ComponentLoader />
      )}
      <Dialog
        open={openDeleteModal}
        onClose={handleCloseDeleteModal}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">Delete Bourbon</DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            Are you sure you want to delete this Bourbon?
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleDeleteClick}>Yes</Button>
          <Button onClick={handleCloseDeleteModal} autoFocus>
            No
          </Button>
        </DialogActions>
      </Dialog>

      <Dialog
        open={openDeleteCustomerModal}
        onClose={handleCloseDeleteCustomerModal}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">UnAssign Customer</DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            Are you sure you want to UnAssign this Customer?
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleDeleteCustomerClick}>Yes</Button>
          <Button onClick={handleCloseDeleteCustomerModal} autoFocus>
            No
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}

export default DetailDistillery;
