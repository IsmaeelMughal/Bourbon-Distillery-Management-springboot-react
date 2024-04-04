package vumc.org.springreact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vumc.org.springreact.model.BourbonDistilleryEntity;

public interface BourbonDistilleryRepository extends JpaRepository<BourbonDistilleryEntity, Integer> {
}
