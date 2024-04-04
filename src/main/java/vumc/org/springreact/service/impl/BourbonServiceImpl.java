package vumc.org.springreact.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import vumc.org.springreact.dtos.BourbonDTO;
import vumc.org.springreact.dtos.ResponseDTO;
import vumc.org.springreact.exceptions.InvalidArgumentsException;
import vumc.org.springreact.exceptions.ResourceNotFoundException;
import vumc.org.springreact.model.BourbonEntity;
import vumc.org.springreact.repository.BourbonDistilleryRepository;
import vumc.org.springreact.repository.BourbonRepository;
import vumc.org.springreact.service.BourbonService;
import vumc.org.springreact.utils.Constants;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BourbonServiceImpl implements BourbonService {
    private final BourbonRepository bourbonRepository;
    private final BourbonDistilleryRepository bourbonDistilleryRepository;
    @Override
    public ResponseDTO<BourbonDTO> addBourbon(BourbonDTO bourbonDTO) {
        log.info("BourbonServiceImpl :: addBourbon starts");
        Long startTime = System.currentTimeMillis();
        ResponseDTO<BourbonDTO> responseDTO = new ResponseDTO<>();
        if(bourbonDTO == null){
            throw new InvalidArgumentsException("bourbon can not be empty!");
        }
        BourbonEntity bourbon = new BourbonEntity();
        BeanUtils.copyProperties(bourbonDTO, bourbon);
        BourbonEntity savedBourbon = bourbonRepository.save(bourbon);
        bourbonDTO.setBourbonId(savedBourbon.getBourbonId());
        responseDTO.setData(bourbonDTO);
        responseDTO.setStatusCode(Constants.STATUS_SUCCESS);
        Long endTime = System.currentTimeMillis();
        log.info("BourbonServiceImpl :: addBourbon ends at " + (endTime - startTime) + "ms");
        return responseDTO;
    }

    @Override
    public ResponseDTO<BourbonDTO> getBourbon(Integer bourbonId) {
        log.info("BourbonServiceImpl :: getBourbon starts");
        Long startTime = System.currentTimeMillis();
        ResponseDTO<BourbonDTO> responseDTO = new ResponseDTO<>();
        if(bourbonId == null){
            throw new InvalidArgumentsException("bourbonId can not be empty!");
        }
        BourbonEntity bourbon = bourbonRepository.findById(bourbonId).orElseThrow(()-> new ResourceNotFoundException
                ("No bourbon found with id : "+ bourbonId));
        BourbonDTO bourbonDTO = new BourbonDTO();
        BeanUtils.copyProperties(bourbon, bourbonDTO);
        bourbonDTO.setDistillery(bourbon.getDistillery().getDistilleryId());
        responseDTO.setData(bourbonDTO);
        responseDTO.setStatusCode(Constants.STATUS_SUCCESS);
        Long endTime = System.currentTimeMillis();
        log.info("BourbonServiceImpl :: getBourbon ends at " + (endTime - startTime) + "ms");
        return responseDTO;
    }

    @Override
    public ResponseDTO<List<BourbonDTO>> getAllBourbons() {
        log.info("BourbonServiceImpl :: getAllBourbons starts");
        Long startTime = System.currentTimeMillis();
        ResponseDTO<List<BourbonDTO>> responseDTO = new ResponseDTO<>();
        List<BourbonEntity> bourbonEntities = bourbonRepository.findAll();
        List<BourbonDTO> bourbonDTOS = bourbonEntities.stream().map(bourbonEntity -> {
            BourbonDTO bourbonDTO = new BourbonDTO();
            BeanUtils.copyProperties(bourbonEntity,bourbonDTO);
            bourbonDTO.setDistillery(bourbonEntity.getDistillery().getDistilleryId());
            return bourbonDTO;
        }).toList();
        responseDTO.setData(bourbonDTOS);
        responseDTO.setStatusCode(Constants.STATUS_SUCCESS);
        Long endTime = System.currentTimeMillis();
        log.info("BourbonServiceImpl :: getAllBourbons ends at " + (endTime - startTime) + "ms");
        return responseDTO;
    }

    @Override
    public ResponseDTO<BourbonDTO> editBourbon(BourbonDTO bourbonDTO) {
        log.info("BourbonServiceImpl :: editBourbon starts");
        Long startTime = System.currentTimeMillis();
        ResponseDTO<BourbonDTO> responseDTO = new ResponseDTO<>();
        if(bourbonDTO == null){
            throw new InvalidArgumentsException("bourbon can not be empty!");
        }
        BourbonEntity bourbon = bourbonRepository.findById(bourbonDTO.getBourbonId()).orElseThrow(()-> new
                ResourceNotFoundException("No bourbon found with id : "+ bourbonDTO.getBourbonId()));
        bourbon.setAbv(bourbonDTO.getAbv());
        bourbon.setType(bourbonDTO.getType());
        bourbon.setName(bourbonDTO.getName());
        bourbon.setDistillery(bourbonDistilleryRepository.findById(bourbonDTO.getDistillery()).orElseThrow(
                ()-> new ResourceNotFoundException("No BourbonDistillery found with id : "+ bourbonDTO.getDistillery())
        ));
        bourbonRepository.save(bourbon);
        responseDTO.setData(bourbonDTO);
        responseDTO.setStatusCode(Constants.STATUS_SUCCESS);
        Long endTime = System.currentTimeMillis();
        log.info("BourbonServiceImpl :: editBourbon ends at " + (endTime - startTime) + "ms");
        return responseDTO;
    }

    @Override
    public ResponseDTO<Boolean> deleteBourbon(Integer bourbonId) {
        log.info("BourbonServiceImpl :: deleteBourbon starts");
        Long startTime = System.currentTimeMillis();
        ResponseDTO<Boolean> responseDTO = new ResponseDTO<>();
        if(bourbonId == null){
            throw new InvalidArgumentsException("bourbonId can not be empty!");
        }
        BourbonEntity bourbon = bourbonRepository.findById(bourbonId).orElseThrow(
                ()-> new ResourceNotFoundException("No bourbon found with id : "+ bourbonId));
        bourbonRepository.delete(bourbon);
        responseDTO.setData(true);
        responseDTO.setStatusCode(Constants.STATUS_SUCCESS);
        Long endTime = System.currentTimeMillis();
        log.info("BourbonServiceImpl :: deleteBourbon ends at " + (endTime - startTime) + "ms");
        return responseDTO;
    }
}
