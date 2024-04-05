package vumc.org.springreact.services;

import vumc.org.springreact.dtos.CustomerDTO;
import vumc.org.springreact.dtos.ResponseDTO;

import java.util.List;

public interface CustomerService {
    ResponseDTO<CustomerDTO> addCustomer(CustomerDTO customerDTO);
    ResponseDTO<CustomerDTO> getCustomer(Integer customerId);
    ResponseDTO<List<CustomerDTO>> getAllCustomers();
    ResponseDTO<CustomerDTO> editCustomer(CustomerDTO customerDTO);
    ResponseDTO<Boolean> deleteCustomer(Integer customerId);
}
