package vumc.org.springreact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vumc.org.springreact.model.BourbonEntity;

@Repository
public interface BourbonRepository extends JpaRepository<BourbonEntity, Integer> {
}
