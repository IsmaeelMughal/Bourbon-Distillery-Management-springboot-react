package vumc.org.springreact.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "bourbon_distillery")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BourbonDistilleryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "distillery_id")
    private Integer distilleryId;
    @Column(name = "name")
    @Basic
    private String name;
    @Column(name = "license_number")
    @Basic
    private String licenseNumber;
    @Column(name = "address")
    @Basic
    private String address;

    @OneToMany(mappedBy = "distillery", cascade = CascadeType.ALL)
    private Set<BourbonEntity> bourbons;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "distillery_customer",
            joinColumns = @JoinColumn(name = "distillery_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private Set<CustomerEntity> customers;
}
