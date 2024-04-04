package vumc.org.springreact.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vumc.org.springreact.model.BourbonDistillery;
import vumc.org.springreact.repository.BourbonDistilleryRepository;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class BourbonDistilleryController {

    private final BourbonDistilleryRepository bourbonDistilleryRepository;

    public BourbonDistilleryController(BourbonDistilleryRepository bourbonDistilleryRepository) {
        this.bourbonDistilleryRepository = bourbonDistilleryRepository;
    }

    @GetMapping("/distilleries")
    Collection<BourbonDistillery> distilleries() {
        return bourbonDistilleryRepository.findAll();
    }
}
