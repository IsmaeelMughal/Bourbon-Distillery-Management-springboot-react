package vumc.org.springreact.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vumc.org.springreact.dtos.CustomerDTO;
import vumc.org.springreact.dtos.ResponseDTO;
import vumc.org.springreact.services.CustomerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseDTO<CustomerDTO> addCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.addCustomer(customerDTO);
    }
    @GetMapping
    public ResponseDTO<List<CustomerDTO>> getAllCustomers(){
        return customerService.getAllCustomers();
    }
    @GetMapping("/{customerId}")
    public ResponseDTO<CustomerDTO> getCustomer(@PathVariable Integer customerId){
        return customerService.getCustomer(customerId);
    }
    @PutMapping
    public ResponseDTO<CustomerDTO> editCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.editCustomer(customerDTO);
    }
    @DeleteMapping("/{customerId}")
    public ResponseDTO<Boolean> deleteCustomer(@PathVariable Integer customerId){
        return customerService.deleteCustomer(customerId);
    }
}
