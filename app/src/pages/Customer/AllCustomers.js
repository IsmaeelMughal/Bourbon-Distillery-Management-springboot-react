import React, { useState, useEffect } from "react";
import { ToastContainer, toast } from "react-toastify";
import CustomerCard from "../../components/CustomerCard";
import { deleteCustomer, getAllCustomers } from "../../services/CustomerAPI";
import ComponentLoader from "../../components/ComponentLoader";

function AllCustomers() {
  const [listOfCustomers, setListOfCustomers] = useState([]);

  const [isLoading, setIsLoading] = useState(false);

  const handleDeleteCustomer = async (customerId) => {
    try {
      const res = await deleteCustomer(customerId);
      if (res.statusCode === 200) {
        const remCustomers = listOfCustomers.filter(
          (customer) => customer.customerId !== customerId
        );
        setListOfCustomers(remCustomers);
        toast.success("Customer Deleted Successfully!!!");
      } else {
        toast.error(res.errorMessage);
      }
    } catch (error) {
      toast.error("Failed to Delete Customer!!!");
    }
  };

  const fetchAllCustomers = async () => {
    try {
      setIsLoading(true);
      const res = await getAllCustomers();
      if (res.statusCode === 200) {
        setListOfCustomers(res.data);
      } else {
        toast.error("Failed To Fetch Customers");
      }
    } catch (err) {
      toast.error("Failed To Fetch Customers");
    } finally {
      setIsLoading(false);
    }
  };
  useEffect(() => {
    fetchAllCustomers();
  }, []);
  return (
    <div>
      <ToastContainer />
      <div className="container px-4 px-lg-5">
        <div className="row gx-4 gx-lg-5 justify-content-center">
          <h1 className="text-center">All Customers</h1>
          {isLoading ? (
            <ComponentLoader />
          ) : (
            <div className="col-md-10 col-lg-8 col-xl-7">
              {listOfCustomers && listOfCustomers.length > 0
                ? listOfCustomers.map((customer) => {
                    return (
                      <CustomerCard
                        customerData={customer}
                        handleDeleteCustomer={handleDeleteCustomer}
                        key={customer.customerId}
                      />
                    );
                  })
                : "NO CUSTOMER FOUND"}
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default AllCustomers;
