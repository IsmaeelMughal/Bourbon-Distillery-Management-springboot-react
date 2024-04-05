package vumc.org.springreact.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vumc.org.springreact.model.BourbonEntity;
import vumc.org.springreact.model.CustomerEntity;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BourbonDistilleryDTO {
    private Integer distilleryId;
    private String name;
    private String licenseNumber;
    private String address;
}
