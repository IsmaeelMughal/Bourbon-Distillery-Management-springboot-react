package vumc.org.springreact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vumc.org.springreact.dtos.BourbonDTO;
import vumc.org.springreact.dtos.ResponseDTO;
import vumc.org.springreact.enums.BourbonType;
import vumc.org.springreact.exceptions.InvalidArgumentsException;
import vumc.org.springreact.exceptions.ResourceNotFoundException;
import vumc.org.springreact.models.BourbonDistilleryEntity;
import vumc.org.springreact.models.BourbonEntity;
import vumc.org.springreact.repositories.BourbonDistilleryRepository;
import vumc.org.springreact.repositories.BourbonRepository;
import vumc.org.springreact.services.impl.BourbonServiceImpl;
import vumc.org.springreact.utils.Constants;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BourbonServiceImplTest {

    @Mock
    private BourbonRepository bourbonRepository;

    @Mock
    private BourbonDistilleryRepository bourbonDistilleryRepository;

    @InjectMocks
    private BourbonServiceImpl bourbonService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddBourbon() {
        // Arrange
        BourbonDTO dto = new BourbonDTO();
        dto.setName("Test Bourbon");
        dto.setAbv(40.0);
        dto.setType(BourbonType.HIGH_CORN);
        dto.setDistillery(1);

        BourbonDistilleryEntity distilleryEntity = new BourbonDistilleryEntity();
        distilleryEntity.setDistilleryId(1);

        when(bourbonDistilleryRepository.findById(1)).thenReturn(Optional.of(distilleryEntity));
        when(bourbonRepository.save(any(BourbonEntity.class))).thenAnswer(invocation -> {
            BourbonEntity bourbonEntity = invocation.getArgument(0);
            bourbonEntity.setBourbonId(1);
            return bourbonEntity;
        });

        // Act
        ResponseDTO<BourbonDTO> response = bourbonService.addBourbon(dto);

        // Assert
        assertEquals(Constants.STATUS_CREATED, response.getStatusCode());
        assertNotNull(response.getData());
        assertEquals(1, response.getData().getBourbonId());
    }

    @Test
    void testGetBourbon() {
        // Arrange
        int bourbonId = 1;

        BourbonEntity entity = new BourbonEntity();
        entity.setBourbonId(bourbonId);
        entity.setName("Test Bourbon");
        entity.setDistillery(new BourbonDistilleryEntity());

        when(bourbonRepository.findById(bourbonId)).thenReturn(Optional.of(entity));

        // Act
        ResponseDTO<BourbonDTO> response = bourbonService.getBourbon(bourbonId);

        // Assert
        assertEquals(Constants.STATUS_SUCCESS, response.getStatusCode());
        assertNotNull(response.getData());
        assertEquals(bourbonId, response.getData().getBourbonId());
    }

    @Test
    void testGetBourbon_NotFound() {
        // Arrange
        int bourbonId = 1;

        when(bourbonRepository.findById(bourbonId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> bourbonService.getBourbon(bourbonId));
    }

    @Test
    void testGetAllBourbons() {
        // Arrange
        BourbonEntity entity = new BourbonEntity();
        entity.setBourbonId(1);
        entity.setName("Test Bourbon");
        entity.setDistillery(new BourbonDistilleryEntity());

        when(bourbonRepository.findAll()).thenReturn(Collections.singletonList(entity));

        // Act
        ResponseDTO<List<BourbonDTO>> response = bourbonService.getAllBourbons();

        // Assert
        assertEquals(Constants.STATUS_SUCCESS, response.getStatusCode());
        assertNotNull(response.getData());
        assertEquals(1, response.getData().size());
    }

    @Test
    void testEditBourbon() {
        // Arrange
        BourbonDTO dto = new BourbonDTO();
        dto.setBourbonId(1);
        dto.setName("Updated Bourbon");
        dto.setAbv(45.0);
        dto.setType(BourbonType.CASK_STRENGTH);
        dto.setDistillery(1);

        BourbonEntity entity = new BourbonEntity();
        entity.setBourbonId(1);
        entity.setName("Test Bourbon");

        when(bourbonRepository.findById(1)).thenReturn(Optional.of(entity));
        when(bourbonRepository.save(any(BourbonEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(bourbonDistilleryRepository.findById(any())).thenReturn(Optional.of(new BourbonDistilleryEntity()));

        // Act
        ResponseDTO<BourbonDTO> response = bourbonService.editBourbon(dto);

        // Assert
        assertEquals(Constants.STATUS_SUCCESS, response.getStatusCode());
        assertNotNull(response.getData());
        assertEquals(1, response.getData().getBourbonId());
        assertEquals("Updated Bourbon", response.getData().getName());
    }

    @Test
    void testDeleteBourbon() {
        // Arrange
        int bourbonId = 1;

        BourbonEntity entity = new BourbonEntity();
        entity.setBourbonId(bourbonId);
        entity.setName("Test Bourbon");

        when(bourbonRepository.findById(bourbonId)).thenReturn(Optional.of(entity));

        // Act
        ResponseDTO<Boolean> response = bourbonService.deleteBourbon(bourbonId);

        // Assert
        assertEquals(Constants.STATUS_SUCCESS, response.getStatusCode());
        assertTrue(response.getData());
        verify(bourbonRepository, times(1)).delete(entity);
    }
}

