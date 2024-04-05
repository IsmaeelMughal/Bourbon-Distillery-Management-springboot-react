package vumc.org.springreact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vumc.org.springreact.dtos.BourbonDistilleryDTO;
import vumc.org.springreact.dtos.ResponseDTO;
import vumc.org.springreact.exceptions.InvalidArgumentsException;
import vumc.org.springreact.exceptions.ResourceNotFoundException;
import vumc.org.springreact.model.BourbonDistilleryEntity;
import vumc.org.springreact.repository.BourbonDistilleryRepository;
import vumc.org.springreact.service.impl.BourbonDistilleryServiceImpl;
import vumc.org.springreact.utils.Constants;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BourbonDistilleryServiceImplTest {

    @Mock
    private BourbonDistilleryRepository bourbonDistilleryRepository;

    @InjectMocks
    private BourbonDistilleryServiceImpl bourbonDistilleryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddBourbonDistillery() {
        
        BourbonDistilleryDTO dto = new BourbonDistilleryDTO();
        dto.setName("Test Distillery");

        BourbonDistilleryEntity entity = new BourbonDistilleryEntity();
        entity.setDistilleryId(1);
        entity.setName("Test Distillery");

        when(bourbonDistilleryRepository.save(any(BourbonDistilleryEntity.class))).thenReturn(entity);

        
        ResponseDTO<BourbonDistilleryDTO> response = bourbonDistilleryService.addBourbonDistillery(dto);

        
        assertEquals(Constants.STATUS_SUCCESS, response.getStatusCode());
        assertNotNull(response.getData());
        assertEquals(1, response.getData().getDistilleryId());
    }

    @Test
    void testGetBourbonDistillery() {
        
        int distilleryId = 1;

        BourbonDistilleryEntity entity = new BourbonDistilleryEntity();
        entity.setDistilleryId(distilleryId);
        entity.setName("Test Distillery");

        when(bourbonDistilleryRepository.findById(distilleryId)).thenReturn(Optional.of(entity));

        
        ResponseDTO<BourbonDistilleryDTO> response = bourbonDistilleryService.getBourbonDistillery(distilleryId);

        
        assertEquals(Constants.STATUS_SUCCESS, response.getStatusCode());
        assertNotNull(response.getData());
        assertEquals(distilleryId, response.getData().getDistilleryId());
    }

    @Test
    void testGetBourbonDistillery_NotFound() {
        
        int distilleryId = 1;

        when(bourbonDistilleryRepository.findById(distilleryId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> bourbonDistilleryService.getBourbonDistillery(distilleryId));
    }

    @Test
    void testGetAllBourbonDistilleries() {
        
        BourbonDistilleryEntity entity = new BourbonDistilleryEntity();
        entity.setDistilleryId(1);
        entity.setName("Test Distillery");

        when(bourbonDistilleryRepository.findAll()).thenReturn(Collections.singletonList(entity));

        
        ResponseDTO<List<BourbonDistilleryDTO>> response = bourbonDistilleryService.getAllBourbonDistilleries();

        
        assertEquals(Constants.STATUS_SUCCESS, response.getStatusCode());
        assertNotNull(response.getData());
        assertEquals(1, response.getData().size());
    }

    @Test
    void testEditBourbonDistillery() {
        
        BourbonDistilleryDTO dto = new BourbonDistilleryDTO();
        dto.setDistilleryId(1);
        dto.setName("Updated Distillery");

        BourbonDistilleryEntity entity = new BourbonDistilleryEntity();
        entity.setDistilleryId(1);
        entity.setName("Test Distillery");

        when(bourbonDistilleryRepository.save(any(BourbonDistilleryEntity.class))).thenReturn(entity);

        
        ResponseDTO<BourbonDistilleryDTO> response = bourbonDistilleryService.editBourbonDistillery(dto);

        
        assertEquals(Constants.STATUS_SUCCESS, response.getStatusCode());
        assertNotNull(response.getData());
        assertEquals(1, response.getData().getDistilleryId());
        assertEquals("Updated Distillery", response.getData().getName());
    }

    @Test
    void testDeleteBourbonDistillery() {
        
        int distilleryId = 1;

        BourbonDistilleryEntity entity = new BourbonDistilleryEntity();
        entity.setDistilleryId(distilleryId);
        entity.setName("Test Distillery");
        entity.setBourbons(new HashSet<>());
        entity.setCustomers(new HashSet<>());

        when(bourbonDistilleryRepository.findById(distilleryId)).thenReturn(Optional.of(entity));

        
        ResponseDTO<Boolean> response = bourbonDistilleryService.deleteBourbonDistillery(distilleryId);

        
        assertEquals(Constants.STATUS_SUCCESS, response.getStatusCode());
        assertTrue(response.getData());
        verify(bourbonDistilleryRepository, times(1)).delete(entity);
    }
}

