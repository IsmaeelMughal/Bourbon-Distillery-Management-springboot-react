package vumc.org.springreact.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;
    @Column(name = "name")
    @Basic
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @ManyToMany(mappedBy = "customers")
    private Set<BourbonDistilleryEntity> distilleries;
}
