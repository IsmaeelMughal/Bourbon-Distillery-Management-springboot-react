package vumc.org.springreact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vumc.org.springreact.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
