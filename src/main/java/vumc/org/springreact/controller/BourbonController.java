package vumc.org.springreact.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vumc.org.springreact.dtos.BourbonDTO;
import vumc.org.springreact.dtos.ResponseDTO;
import vumc.org.springreact.service.BourbonService;

import java.util.List;

@RestController
@RequestMapping("/api/bourbons")
@RequiredArgsConstructor
public class BourbonController {
    private final BourbonService bourbonService;

    @PostMapping
    ResponseDTO<BourbonDTO> addBourbon(@RequestBody BourbonDTO bourbonDTO){
        return bourbonService.addBourbon(bourbonDTO);
    }
    @GetMapping("/{bourbonId}")
    ResponseDTO<BourbonDTO> getBourbon(@PathVariable Integer bourbonId){
        return  bourbonService.getBourbon(bourbonId);
    }
    @GetMapping
    ResponseDTO<List<BourbonDTO>> getAllBourbons(){
        return bourbonService.getAllBourbons();
    }
    @PutMapping
    ResponseDTO<BourbonDTO> editBourbon(@RequestBody BourbonDTO bourbonDTO){
        return bourbonService.editBourbon(bourbonDTO);
    }
    @DeleteMapping("/{bourbonId}")
    ResponseDTO<Boolean> deleteBourbon(@PathVariable Integer bourbonId){
        return bourbonService.deleteBourbon(bourbonId);
    }
}
