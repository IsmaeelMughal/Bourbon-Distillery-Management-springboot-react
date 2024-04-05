package vumc.org.springreact.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vumc.org.springreact.models.BourbonDistilleryEntity;

public interface BourbonDistilleryRepository extends JpaRepository<BourbonDistilleryEntity, Integer> {
}
