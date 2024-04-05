import { myAxios } from "./AxiosHelper";

export const addBourbon = async (bourbonData) => {
  try {
    const endpoint = `/bourbons`;
    const res = await myAxios.post(endpoint, bourbonData);
    return res.data;
  } catch (error) {
    throw error;
  }
};
export const deleteBourbon = async (bourbonId) => {
  try {
    const endpoint = `/bourbons/${bourbonId}`;
    const res = await myAxios.delete(endpoint);
    return res.data;
  } catch (error) {
    throw error;
  }
};
