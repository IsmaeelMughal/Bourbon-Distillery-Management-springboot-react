import { myAxios } from "./AxiosHelper";

export const getAllCustomers = async () => {
  try {
    const endpoint = `/customers`;
    const res = await myAxios.get(endpoint);
    return res.data;
  } catch (error) {
    throw error;
  }
};

export const deleteCustomer = async (customerId) => {
  try {
    const endpoint = `/customers/${customerId}`;
    const res = await myAxios.delete(endpoint);
    return res.data;
  } catch (error) {
    throw error;
  }
};

export const addCustomer = async (customerData) => {
  try {
    const endpoint = `/customers`;
    const res = await myAxios.post(endpoint, customerData);
    return res.data;
  } catch (error) {
    throw error;
  }
};

export const editCustomer = async (customerData) => {
  try {
    const endpoint = `/customers`;
    const res = await myAxios.put(endpoint, customerData);
    return res.data;
  } catch (error) {
    throw error;
  }
};

export const getUnassignedCustomers = async (distilleryId) => {
  try {
    const endpoint = `/customers/unassigned/${distilleryId}`;
    const res = await myAxios.get(endpoint);
    return res.data;
  } catch (error) {
    throw error;
  }
};

export const assignCustomerToDistillery = async (distilleryId, customerId) => {
  try {
    const endpoint = `/customers/${distilleryId}/${customerId}`;
    const res = await myAxios.get(endpoint);
    return res.data;
  } catch (error) {
    throw error;
  }
};

export const unAssignCustomerFromDistillery = async (
  distilleryId,
  customerId
) => {
  try {
    const endpoint = `/customers/unassign/${distilleryId}/${customerId}`;
    const res = await myAxios.post(endpoint, {});
    return res.data;
  } catch (error) {
    throw error;
  }
};
