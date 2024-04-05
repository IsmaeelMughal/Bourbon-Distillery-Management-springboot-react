package vumc.org.springreact.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import vumc.org.springreact.dtos.CustomerDTO;
import vumc.org.springreact.dtos.ResponseDTO;
import vumc.org.springreact.exceptions.InvalidArgumentsException;
import vumc.org.springreact.exceptions.ResourceNotFoundException;
import vumc.org.springreact.model.CustomerEntity;
import vumc.org.springreact.repository.CustomerRepository;
import vumc.org.springreact.service.CustomerService;
import vumc.org.springreact.utils.Constants;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public ResponseDTO<CustomerDTO> addCustomer(CustomerDTO customerDTO) {
        log.info("CustomerServiceImpl :: addCustomer starts");
        Long startTime = System.currentTimeMillis();
        ResponseDTO<CustomerDTO> responseDTO = new ResponseDTO<>();
        if(customerDTO == null){
            throw new InvalidArgumentsException("Customer can not be empty!!");
        }
        CustomerEntity customer = new CustomerEntity();
        BeanUtils.copyProperties(customerDTO,customer);
        CustomerEntity savedCustomer = customerRepository.save(customer);
        customerDTO.setId(savedCustomer.getCustomerId());
        responseDTO.setData(customerDTO);
        responseDTO.setStatusCode(Constants.STATUS_CREATED);
        Long endTime = System.currentTimeMillis();
        log.info("CustomerServiceImpl :: addCustomer ends at " + (endTime - startTime) + "ms");
        return responseDTO;
    }

    @Override
    public ResponseDTO<CustomerDTO> getCustomer(Integer customerId) {
        log.info("CustomerServiceImpl :: getCustomer starts");
        Long startTime = System.currentTimeMillis();
        ResponseDTO<CustomerDTO> responseDTO = new ResponseDTO<>();
        if(customerId == null){
            throw new InvalidArgumentsException("CustomerId can not be empty!!");
        }
        CustomerEntity customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer with with Id " + customerId + " does not Exist!"));
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        responseDTO.setData(customerDTO);
        responseDTO.setStatusCode(Constants.STATUS_SUCCESS);
        Long endTime = System.currentTimeMillis();
        log.info("CustomerServiceImpl :: getCustomer ends at " + (endTime - startTime) + "ms");
        return responseDTO;
    }

    @Override
    public ResponseDTO<List<CustomerDTO>> getAllCustomers() {
        log.info("CustomerServiceImpl :: getAllCustomer starts");
        Long startTime = System.currentTimeMillis();
        ResponseDTO<List<CustomerDTO>> responseDTO = new ResponseDTO<>();
        List<CustomerEntity> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOs = customers.stream().map(customer -> {
            CustomerDTO customerDTO = new CustomerDTO();
            BeanUtils.copyProperties(customer, customerDTO);
            return customerDTO;
        }).toList();
        responseDTO.setData(customerDTOs);
        responseDTO.setStatusCode(Constants.STATUS_SUCCESS);
        Long endTime = System.currentTimeMillis();
        log.info("CustomerServiceImpl :: getAllCustomer ends at " + (endTime - startTime) + "ms");
        return responseDTO;
    }

    @Override
    public ResponseDTO<CustomerDTO> editCustomer(CustomerDTO customerDTO) {
        log.info("CustomerServiceImpl :: editCustomer starts");
        Long startTime = System.currentTimeMillis();
        ResponseDTO<CustomerDTO> responseDTO = new ResponseDTO<>();
        if(customerDTO == null){
            throw new InvalidArgumentsException("Customer can not be empty!!");
        }
        CustomerEntity customer = new CustomerEntity();
        customer.setName(customer.getName());
        customer.setPhoneNumber(customer.getPhoneNumber());
        customerRepository.save(customer);
        responseDTO.setData(customerDTO);
        responseDTO.setStatusCode(Constants.STATUS_SUCCESS);
        Long endTime = System.currentTimeMillis();
        log.info("CustomerServiceImpl :: editCustomer ends at " + (endTime - startTime) + "ms");
        return responseDTO;
    }

    @Override
    public ResponseDTO<Boolean> deleteCustomer(Integer customerId) {
        log.info("CustomerServiceImpl :: deleteCustomer starts");
        Long startTime = System.currentTimeMillis();
        ResponseDTO<Boolean> responseDTO = new ResponseDTO<>();
        if(customerId == null){
            throw new InvalidArgumentsException("CustomerId can not be empty!!");
        }
        CustomerEntity customer = customerRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("Customer with with Id " + customerId + " does not Exist!"));
        customer.getDistilleries().clear();
        customer.setDistilleries(null);
        customerRepository.delete(customer);
        responseDTO.setData(true);
        responseDTO.setStatusCode(Constants.STATUS_SUCCESS);
        Long endTime = System.currentTimeMillis();
        log.info("CustomerServiceImpl :: deleteCustomer ends at " + (endTime - startTime) + "ms");
        return responseDTO;
    }

}
