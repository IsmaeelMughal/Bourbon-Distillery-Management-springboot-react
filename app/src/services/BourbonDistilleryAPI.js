import { myAxios } from "./AxiosHelper";

export const getAllBourbonDistilleries = async () => {
  try {
    const endpoint = `/distilleries`;
    const res = await myAxios.get(endpoint);
    return res.data;
  } catch (error) {
    throw error;
  }
};

export const getDistilleryDetails = async (distilleryId) => {
  try {
    const endpoint = `/distilleries/${distilleryId}`;
    const res = await myAxios.get(endpoint);
    return res.data;
  } catch (error) {
    throw error;
  }
};

export const addBourbonDistillery = async (distilleryData) => {
  try {
    const endpoint = `/distilleries`;
    const res = await myAxios.post(endpoint, distilleryData);
    return res.data;
  } catch (error) {
    throw error;
  }
};

export const editBourbonDistillery = async (distilleryData) => {
  try {
    const endpoint = `/distilleries`;
    const res = await myAxios.put(endpoint, distilleryData);
    return res.data;
  } catch (error) {
    throw error;
  }
};

export const deleteBourbonDistillery = async (distilleryId) => {
  try {
    const endpoint = `/distilleries/${distilleryId}`;
    const res = await myAxios.delete(endpoint);
    return res.data;
  } catch (error) {
    throw error;
  }
};


