package vumc.org.springreact;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vumc.org.springreact.model.BourbonDistillery;
import vumc.org.springreact.repository.BourbonDistilleryRepository;

import java.util.stream.Stream;

@Component
public class Initializer implements CommandLineRunner {
    private final BourbonDistilleryRepository bourbonDistilleryRepository;

    public Initializer(BourbonDistilleryRepository bourbonDistilleryRepository) {
        this.bourbonDistilleryRepository = bourbonDistilleryRepository;
    }

    @Override
    public void run(String... args) {
        Stream.of("Buffalo Trace", "Woodford Reserve", "Castle & Key", "Four Roses")
                .forEach(name -> bourbonDistilleryRepository.save(new BourbonDistillery(name)));
        bourbonDistilleryRepository.findAll().forEach(System.out::println);
    }
}
