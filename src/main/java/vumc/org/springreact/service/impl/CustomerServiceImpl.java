package vumc.org.springreact.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import vumc.org.springreact.dtos.CustomerDTO;
import vumc.org.springreact.dtos.ResponseDTO;
import vumc.org.springreact.model.Customer;
import vumc.org.springreact.repository.CustomerRepository;
import vumc.org.springreact.service.CustomerService;
import vumc.org.springreact.utils.Constants;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public ResponseDTO<CustomerDTO> addCustomer(CustomerDTO customerDTO) {
        log.info("CustomerServiceImpl :: addLevelFourAccount starts");
        Long startTime = System.currentTimeMillis();
        ResponseDTO<CustomerDTO> responseDTO = new ResponseDTO<>();
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        Customer savedCustomer = customerRepository.save(customer);
        customerDTO.setId(savedCustomer.getId());
        responseDTO.setData(customerDTO);
        responseDTO.setStatusCode(Constants.STATUS_SUCCESS);
        Long endTime = System.currentTimeMillis();
        log.info("CustomerServiceImpl :: addLevelFourAccount ends at " + (endTime - startTime) + "ms");
        return responseDTO;
    }

    @Override
    public ResponseDTO<CustomerDTO> getCustomer(Integer customerId) {
        return null;
    }
}
