package vumc.org.springreact.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import vumc.org.springreact.dtos.BourbonDistilleryDTO;
import vumc.org.springreact.dtos.ResponseDTO;
import vumc.org.springreact.exceptions.InvalidArgumentsException;
import vumc.org.springreact.exceptions.ResourceNotFoundException;
import vumc.org.springreact.models.BourbonDistilleryEntity;
import vumc.org.springreact.repositories.BourbonDistilleryRepository;
import vumc.org.springreact.services.BourbonDistilleryService;
import vumc.org.springreact.utils.Constants;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BourbonDistilleryServiceImpl implements BourbonDistilleryService {
    private final BourbonDistilleryRepository bourbonDistilleryRepository;
    @Override
    public ResponseDTO<BourbonDistilleryDTO> addBourbonDistillery(BourbonDistilleryDTO bourbonDistilleryDTO) {
        log.info("BourbonDistilleryServiceImpl :: addBourbonDistillery starts");
        Long startTime = System.currentTimeMillis();
        ResponseDTO<BourbonDistilleryDTO> responseDTO = new ResponseDTO<>();
        if(bourbonDistilleryDTO==null){
            throw new InvalidArgumentsException("Bourbon Distillery can not be empty!");
        }
        BourbonDistilleryEntity bourbonDistillery = new BourbonDistilleryEntity();
        BeanUtils.copyProperties(bourbonDistilleryDTO,bourbonDistillery);
        BourbonDistilleryEntity savedBourbonDistillery =  bourbonDistilleryRepository.save(bourbonDistillery);
        bourbonDistilleryDTO.setDistilleryId(savedBourbonDistillery.getDistilleryId());
        responseDTO.setData(bourbonDistilleryDTO);
        responseDTO.setStatusCode(Constants.STATUS_SUCCESS);
        Long endTime = System.currentTimeMillis();
        log.info("BourbonDistilleryServiceImpl :: addBourbonDistillery ends at " + (endTime - startTime) + "ms");
        return responseDTO;
    }

    @Override
    public ResponseDTO<BourbonDistilleryDTO> getBourbonDistillery(Integer distilleryId) {
        log.info("BourbonDistilleryServiceImpl :: getBourbonDistillery starts");
        Long startTime = System.currentTimeMillis();
        ResponseDTO<BourbonDistilleryDTO> responseDTO = new ResponseDTO<>();
        if(distilleryId == null){
            throw new InvalidArgumentsException("DistilleryId can not be empty!");
        }
        BourbonDistilleryEntity bourbonDistillery = bourbonDistilleryRepository.findById(distilleryId).orElseThrow(
                ()-> new ResourceNotFoundException("Bourbon Distillery with id: "+ distilleryId + " not found!"));
        BourbonDistilleryDTO bourbonDistilleryDTO = new BourbonDistilleryDTO();
        BeanUtils.copyProperties(bourbonDistillery,bourbonDistilleryDTO);
        responseDTO.setData(bourbonDistilleryDTO);
        responseDTO.setStatusCode(Constants.STATUS_SUCCESS);
        Long endTime = System.currentTimeMillis();
        log.info("BourbonDistilleryServiceImpl :: getBourbonDistillery ends at " + (endTime - startTime) + "ms");
        return responseDTO;
    }

    @Override
    public ResponseDTO<List<BourbonDistilleryDTO>> getAllBourbonDistilleries() {
        log.info("BourbonDistilleryServiceImpl :: getAllBourbonDistilleries starts");
        Long startTime = System.currentTimeMillis();
        ResponseDTO<List<BourbonDistilleryDTO>> responseDTO = new ResponseDTO<>();
        List<BourbonDistilleryEntity> distilleryEntities = bourbonDistilleryRepository.findAll();
        List<BourbonDistilleryDTO> bourbonDistilleryDTOS = distilleryEntities.stream().map(bourbonDistilleryEntity -> {
            BourbonDistilleryDTO bourbonDistilleryDTO = new BourbonDistilleryDTO();
            BeanUtils.copyProperties(bourbonDistilleryEntity,bourbonDistilleryDTO);
            return bourbonDistilleryDTO;
        }).toList();
        responseDTO.setData(bourbonDistilleryDTOS);
        responseDTO.setStatusCode(Constants.STATUS_SUCCESS);
        Long endTime = System.currentTimeMillis();
        log.info("BourbonDistilleryServiceImpl :: getAllBourbonDistilleries ends at " + (endTime - startTime) + "ms");
        return responseDTO;

    }

    @Override
    public ResponseDTO<BourbonDistilleryDTO> editBourbonDistillery(BourbonDistilleryDTO bourbonDistilleryDTO) {
        log.info("BourbonDistilleryServiceImpl :: editBourbonDistillery starts");
        Long startTime = System.currentTimeMillis();
        ResponseDTO<BourbonDistilleryDTO> responseDTO = new ResponseDTO<>();
        if(bourbonDistilleryDTO == null){
            throw new InvalidArgumentsException("Bourbon Distillery can not be empty!");
        }
        BourbonDistilleryEntity bourbonDistillery = new BourbonDistilleryEntity();
        BeanUtils.copyProperties(bourbonDistilleryDTO,bourbonDistillery);
        bourbonDistilleryRepository.save(bourbonDistillery);
        responseDTO.setData(bourbonDistilleryDTO);
        responseDTO.setStatusCode(Constants.STATUS_SUCCESS);
        Long endTime = System.currentTimeMillis();
        log.info("BourbonDistilleryServiceImpl :: editBourbonDistillery ends at " + (endTime - startTime) + "ms");
        return responseDTO;
    }

    @Override
    public ResponseDTO<Boolean> deleteBourbonDistillery(Integer distilleryId) {
        log.info("BourbonDistilleryServiceImpl :: deleteBourbonDistillery starts");
        Long startTime = System.currentTimeMillis();
        ResponseDTO<Boolean> responseDTO = new ResponseDTO<>();
        if(distilleryId == null){
            throw new InvalidArgumentsException("DistilleryId can not be empty!");
        }
        BourbonDistilleryEntity bourbonDistillery = bourbonDistilleryRepository.findById(distilleryId).orElseThrow(
                ()-> new ResourceNotFoundException("No Bourbon Distillery with id: " + distilleryId + " found!"));
        bourbonDistillery.getBourbons().clear();
        bourbonDistillery.setBourbons(null);
        bourbonDistillery.getCustomers().clear();
        bourbonDistillery.setCustomers(null);
        bourbonDistilleryRepository.delete(bourbonDistillery);
        responseDTO.setData(true);
        responseDTO.setStatusCode(Constants.STATUS_SUCCESS);
        Long endTime = System.currentTimeMillis();
        log.info("BourbonDistilleryServiceImpl :: deleteBourbonDistillery ends at " + (endTime - startTime) + "ms");
        return responseDTO;
    }
}
