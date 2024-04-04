package vumc.org.springreact.model;

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
public class Bourbon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "distillery_id")
    private BourbonDistillery distillery;

    private String name;

    @Enumerated(EnumType.STRING)
    private BourbonType type;

    private double abv;
}
