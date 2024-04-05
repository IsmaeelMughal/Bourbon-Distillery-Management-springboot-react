package vumc.org.springreact.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vumc.org.springreact.models.BourbonEntity;

import java.util.List;

@Repository
public interface BourbonRepository extends JpaRepository<BourbonEntity, Integer> {
    List<BourbonEntity> findByDistillery_DistilleryId(Integer distilleryId);
}
