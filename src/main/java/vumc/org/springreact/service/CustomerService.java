package vumc.org.springreact.service;

import vumc.org.springreact.dtos.CustomerDTO;
import vumc.org.springreact.dtos.ResponseDTO;

public interface CustomerService {
    ResponseDTO<CustomerDTO> addCustomer(CustomerDTO customerDTO);
    ResponseDTO<CustomerDTO> getCustomer(Integer customerId);
}
