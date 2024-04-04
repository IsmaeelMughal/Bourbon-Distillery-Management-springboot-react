package vumc.org.springreact.service;

import vumc.org.springreact.dtos.BourbonDTO;
import vumc.org.springreact.dtos.ResponseDTO;

import java.util.List;

public interface BourbonService {
    ResponseDTO<BourbonDTO> addBourbon(BourbonDTO bourbonDTO);
    ResponseDTO<BourbonDTO> getBourbon(Integer bourbonId);
    ResponseDTO<List<BourbonDTO>> getAllBourbons();
    ResponseDTO<BourbonDTO> editBourbon(BourbonDTO bourbonDTO);
    ResponseDTO<Boolean> deleteBourbon(Integer bourbonId);
}
