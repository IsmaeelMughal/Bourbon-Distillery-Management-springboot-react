package vumc.org.springreact.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vumc.org.springreact.models.CustomerEntity;

import java.util.List;
import java.util.Set;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

    @Query("SELECT c FROM CustomerEntity c JOIN c.distilleries d WHERE d.distilleryId = :distilleryId")
    Set<CustomerEntity> findCustomersByDistilleryId(@Param("distilleryId") Integer distilleryId);
    @Query("SELECT c FROM CustomerEntity c WHERE c.customerId NOT IN " +
            "(SELECT c2.customerId FROM CustomerEntity c2 JOIN c2.distilleries d WHERE d.distilleryId = :distilleryId)")
    Set<CustomerEntity> findUnassignedCustomers(@Param("distilleryId") Integer distilleryId);
}
