package vumc.org.springreact.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vumc.org.springreact.enums.BourbonType;
import vumc.org.springreact.model.BourbonDistilleryEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BourbonDTO {
    private Integer bourbonId;
    private String name;
    private double abv;
    private BourbonType type;
    private Integer distillery;
}
