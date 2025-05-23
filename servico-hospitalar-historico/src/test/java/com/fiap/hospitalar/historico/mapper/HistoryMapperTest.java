package com.fiap.hospitalar.historico.mapper;

import com.fiap.hospitalar.historico.dto.HistoryDTO;
import com.fiap.hospitalar.historico.model.History;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HistoryMapperTest {

    private final HistoryMapper historyMapper = HistoryMapper.INSTANCE;

    @Test
    @DisplayName("Deve converter History para HistoryDTO")
    public void testHistoryToHistoryDTO() {
        // Arrange
        History history = new History();
        history.setPaciente("Paciente 1");
        history.setMedico("Dr. A");
        history.setEnfermeiro("Enfermeiro X");
        history.setDataHora(LocalDateTime.of(2023, 4, 1, 10, 0));

        // Act
        HistoryDTO historyDTO = historyMapper.historyToHistoryDTO(history);

        // Assert
        assertNotNull(historyDTO);
        assertEquals("Paciente 1", historyDTO.getPaciente());
        assertEquals("Dr. A", historyDTO.getMedico());
        assertEquals("Enfermeiro X", historyDTO.getEnfermeiro());
        assertEquals(LocalDateTime.of(2023, 4, 1, 10, 0), historyDTO.getDataHora());
    }
}