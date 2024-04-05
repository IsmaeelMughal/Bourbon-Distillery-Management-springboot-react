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
