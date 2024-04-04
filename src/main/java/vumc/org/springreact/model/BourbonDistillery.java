package vumc.org.springreact.model;

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
public class BourbonDistillery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String licenseNumber;
    private String address;

    @OneToMany(mappedBy = "distillery")
    private Set<Bourbon> bourbons;

    @ManyToMany
    @JoinTable(
            name = "distillery_customer",
            joinColumns = @JoinColumn(name = "distillery_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private Set<Customer> customers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BourbonDistillery person = (BourbonDistillery) o;
        return id.equals(person.id) && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BourbonDistillery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
