package vumc.org.springreact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vumc.org.springreact.model.BourbonDistillery;

public interface BourbonDistilleryRepository extends JpaRepository<BourbonDistillery, Long> {
}
