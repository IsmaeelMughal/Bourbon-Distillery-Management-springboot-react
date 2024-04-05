package vumc.org.springreact.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BourbonDistilleryDTO {
    private Integer distilleryId;
    private String name;
    private String licenseNumber;
    private String address;
    private Set<BourbonDTO> bourbons;
    private Set<CustomerDTO> customers;
}
