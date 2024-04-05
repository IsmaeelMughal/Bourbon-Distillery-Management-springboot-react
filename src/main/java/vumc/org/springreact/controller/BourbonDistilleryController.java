package vumc.org.springreact.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vumc.org.springreact.dtos.BourbonDistilleryDTO;
import vumc.org.springreact.dtos.ResponseDTO;
import vumc.org.springreact.model.BourbonDistilleryEntity;
import vumc.org.springreact.repository.BourbonDistilleryRepository;
import vumc.org.springreact.service.BourbonDistilleryService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/distilleries")
@RequiredArgsConstructor
public class BourbonDistilleryController {
    private final BourbonDistilleryService bourbonDistilleryService;

    @PostMapping
    ResponseDTO<BourbonDistilleryDTO> addBourbonDistillery(@RequestBody BourbonDistilleryDTO bourbonDistilleryDTO) {
        return bourbonDistilleryService.addBourbonDistillery(bourbonDistilleryDTO);
    }
    @GetMapping("/{distilleryId}")
    ResponseDTO<BourbonDistilleryDTO> getBourbonDistillery(@PathVariable Integer distilleryId){
        return bourbonDistilleryService.getBourbonDistillery(distilleryId);
    }
    @GetMapping
    ResponseDTO<List<BourbonDistilleryDTO>> getAllBourbonDistilleries(){
        return bourbonDistilleryService.getAllBourbonDistilleries();
    }
    @PutMapping
    ResponseDTO<BourbonDistilleryDTO> editBourbonDistillery(@RequestBody BourbonDistilleryDTO bourbonDistilleryDTO){
        return bourbonDistilleryService.editBourbonDistillery(bourbonDistilleryDTO);
    }
    @DeleteMapping("/{distilleryId}")
    ResponseDTO<Boolean> deleteBourbonDistillery(@PathVariable Integer distilleryId){
        return bourbonDistilleryService.deleteBourbonDistillery(distilleryId);
    }
}
