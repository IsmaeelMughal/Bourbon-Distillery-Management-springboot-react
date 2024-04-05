package vumc.org.springreact.service;

import vumc.org.springreact.dtos.BourbonDistilleryDTO;
import vumc.org.springreact.dtos.ResponseDTO;

import java.util.List;

public interface BourbonDistilleryService {
    ResponseDTO<BourbonDistilleryDTO> addBourbonDistillery(BourbonDistilleryDTO bourbonDistilleryDTO);
    ResponseDTO<BourbonDistilleryDTO> getBourbonDistillery(Integer distilleryId);
    ResponseDTO<List<BourbonDistilleryDTO>> getAllBourbonDistilleries();
    ResponseDTO<BourbonDistilleryDTO> editBourbonDistillery(BourbonDistilleryDTO  bourbonDistilleryDTO);
    ResponseDTO<Boolean> deleteBourbonDistillery(Integer distilleryId);
}
