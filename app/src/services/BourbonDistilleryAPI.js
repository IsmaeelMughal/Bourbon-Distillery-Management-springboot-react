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