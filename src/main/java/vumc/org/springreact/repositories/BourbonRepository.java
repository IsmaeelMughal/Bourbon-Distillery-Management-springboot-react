package vumc.org.springreact.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vumc.org.springreact.models.BourbonEntity;

@Repository
public interface BourbonRepository extends JpaRepository<BourbonEntity, Integer> {
}
