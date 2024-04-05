package vumc.org.springreact.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import vumc.org.springreact.dtos.CustomerDTO;
import vumc.org.springreact.dtos.ResponseDTO;
import vumc.org.springreact.exceptions.InvalidArgumentsException;
import vumc.org.springreact.exceptions.ResourceNotFoundException;
import vumc.org.springreact.models.BourbonDistilleryEntity;
import vumc.org.springreact.models.BourbonEntity;
import vumc.org.springreact.models.CustomerEntity;
import vumc.org.springreact.repositories.BourbonDistilleryRepository;
import vumc.org.springreact.repositories.CustomerRepository;
import vumc.org.springreact.services.CustomerService;
import vumc.org.springreact.utils.Constants;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final BourbonDistilleryRepository bourbonDistilleryRepository;
    @Override
    @Transactional
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
        customerDTO.setCustomerId(savedCustomer.getCustomerId());
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

    @Transactional
    @Override
    public ResponseDTO<CustomerDTO> editCustomer(CustomerDTO customerDTO) {
        log.info("CustomerServiceImpl :: editCustomer starts");
        Long startTime = System.currentTimeMillis();
        ResponseDTO<CustomerDTO> responseDTO = new ResponseDTO<>();
        if(customerDTO == null){
            throw new InvalidArgumentsException("Customer can not be empty!!");
        }
        CustomerEntity customer = customerRepository.findById(customerDTO.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("No Customer found with id: "+ customerDTO.getCustomerId())
        );
        customer.setName(customerDTO.getName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customerRepository.save(customer);
        responseDTO.setData(customerDTO);
        responseDTO.setStatusCode(Constants.STATUS_SUCCESS);
        Long endTime = System.currentTimeMillis();
        log.info("CustomerServiceImpl :: editCustomer ends at " + (endTime - startTime) + "ms");
        return responseDTO;
    }

    @Override
    @Transactional
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

    @Override
    @Transactional
    public ResponseDTO<CustomerDTO> assignDistilleryToCustomer(Integer distilleryId, Integer customerId) {
        log.info("CustomerServiceImpl :: assignDistilleryToCustomer starts");
        Long startTime = System.currentTimeMillis();
        ResponseDTO<CustomerDTO> responseDTO = new ResponseDTO<>();
        if(distilleryId == null || customerId == null){
            throw new InvalidArgumentsException("Id can not be null");
        }
        BourbonDistilleryEntity bourbonDistillery = bourbonDistilleryRepository.findById(distilleryId).orElseThrow(
                ()-> new ResourceNotFoundException("No Distillery found with id: "+ distilleryId));

        CustomerEntity customer = customerRepository.findById(customerId).orElseThrow(
                ()-> new ResourceNotFoundException("No Customer found with id: " + customerId));

        Set<CustomerEntity> list = bourbonDistillery.getCustomers();
        list.add(customer);
        bourbonDistillery.setCustomers(list);
        bourbonDistilleryRepository.save(bourbonDistillery);

        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        responseDTO.setData(customerDTO);
        responseDTO.setStatusCode(Constants.STATUS_SUCCESS);
        Long endTime = System.currentTimeMillis();
        log.info("CustomerServiceImpl :: assignDistilleryToCustomer ends at " + (endTime - startTime) + "ms");
        return responseDTO;
    }

    @Override
    public ResponseDTO<List<CustomerDTO>> getUnassignedCustomers(Integer distilleryId) {
        log.info("CustomerServiceImpl :: getUnassignedCustomers starts");
        Long startTime = System.currentTimeMillis();
        ResponseDTO<List<CustomerDTO>> responseDTO = new ResponseDTO<>();
        Set<CustomerEntity> customerEntities = customerRepository.findUnassignedCustomers(distilleryId);
        List<CustomerDTO> customerDTOS = customerEntities.stream().map(customerEntity -> {
            CustomerDTO customerDTO = new CustomerDTO();
            BeanUtils.copyProperties(customerEntity, customerDTO);
            return customerDTO;
        }).toList();
        responseDTO.setData(customerDTOS);
        responseDTO.setStatusCode(Constants.STATUS_SUCCESS);
        Long endTime = System.currentTimeMillis();
        log.info("CustomerServiceImpl :: getUnassignedCustomers ends at " + (endTime - startTime) + "ms");
        return responseDTO;
    }
}
