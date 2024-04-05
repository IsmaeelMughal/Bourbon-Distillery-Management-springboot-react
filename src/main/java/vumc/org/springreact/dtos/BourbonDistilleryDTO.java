package vumc.org.springreact.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BourbonDistilleryDTO {
    private Integer distilleryId;
    private String name;
    private String licenseNumber;
    private String address;
}
