package vumc.org.springreact.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vumc.org.springreact.enums.BourbonType;


@Entity
@Table(name = "bourbon")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BourbonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bourbon_id")
    private Integer bourbonId;
    @Column(name = "name")
    @Basic
    private String name;
    @Column(name = "abv")
    @Basic
    private double abv;
    @Enumerated(EnumType.STRING)
    private BourbonType type;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "distillery_id")
    private BourbonDistilleryEntity distillery;

}
