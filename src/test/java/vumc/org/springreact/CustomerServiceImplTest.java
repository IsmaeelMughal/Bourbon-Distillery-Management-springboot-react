package vumc.org.springreact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vumc.org.springreact.dtos.CustomerDTO;
import vumc.org.springreact.dtos.ResponseDTO;
import vumc.org.springreact.exceptions.InvalidArgumentsException;
import vumc.org.springreact.exceptions.ResourceNotFoundException;
import vumc.org.springreact.models.CustomerEntity;
import vumc.org.springreact.repositories.CustomerRepository;
import vumc.org.springreact.services.impl.CustomerServiceImpl;
import vumc.org.springreact.utils.Constants;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddCustomer() {
        // Arrange
        CustomerDTO dto = new CustomerDTO();
        dto.setName("Test Customer");
        dto.setPhoneNumber("1234567890");

        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerId(1);
        entity.setName("Test Customer");

        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(entity);

        // Act
        ResponseDTO<CustomerDTO> response = customerService.addCustomer(dto);

        // Assert
        assertEquals(Constants.STATUS_CREATED, response.getStatusCode());
        assertNotNull(response.getData());
        assertEquals(1, response.getData().getCustomerId());
    }

    @Test
    void testGetCustomer() {
        // Arrange
        int customerId = 1;

        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerId(customerId);
        entity.setName("Test Customer");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(entity));

        // Act
        ResponseDTO<CustomerDTO> response = customerService.getCustomer(customerId);

        // Assert
        assertEquals(Constants.STATUS_SUCCESS, response.getStatusCode());
        assertNotNull(response.getData());
        assertEquals(customerId, response.getData().getCustomerId());
    }

    @Test
    void testGetCustomer_NotFound() {
        // Arrange
        int customerId = 1;

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> customerService.getCustomer(customerId));
    }

    @Test
    void testGetAllCustomers() {
        // Arrange
        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerId(1);
        entity.setName("Test Customer");

        when(customerRepository.findAll()).thenReturn(Collections.singletonList(entity));

        // Act
        ResponseDTO<List<CustomerDTO>> response = customerService.getAllCustomers();

        // Assert
        assertEquals(Constants.STATUS_SUCCESS, response.getStatusCode());
        assertNotNull(response.getData());
        assertEquals(1, response.getData().size());
    }

    @Test
    void testEditCustomer() {
        // Arrange
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerId(1);
        dto.setName("Updated Customer");
        dto.setPhoneNumber("9876543210");

        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerId(1);
        entity.setName("Test Customer");

        when(customerRepository.save(any(CustomerEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        ResponseDTO<CustomerDTO> response = customerService.editCustomer(dto);

        // Assert
        assertEquals(Constants.STATUS_SUCCESS, response.getStatusCode());
        assertNotNull(response.getData());
        assertEquals(1, response.getData().getCustomerId());
        assertEquals("Updated Customer", response.getData().getName());
    }

    @Test
    void testDeleteCustomer() {
        // Arrange
        int customerId = 1;

        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerId(customerId);
        entity.setName("Test Customer");
        entity.setDistilleries(new HashSet<>());

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(entity));

        // Act
        ResponseDTO<Boolean> response = customerService.deleteCustomer(customerId);

        // Assert
        assertEquals(Constants.STATUS_SUCCESS, response.getStatusCode());
        assertTrue(response.getData());
        verify(customerRepository, times(1)).delete(entity);
    }
}
