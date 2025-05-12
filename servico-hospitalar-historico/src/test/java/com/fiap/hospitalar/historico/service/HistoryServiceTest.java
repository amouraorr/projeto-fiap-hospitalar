package com.fiap.hospitalar.historico.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.hospitalar.historico.model.History;
import com.fiap.hospitalar.historico.repository.HistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class HistoryServiceTest {

    @InjectMocks
    private HistoryService historyService;

    @Mock
    private HistoryRepository historyRepository;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Deve processar consulta com sucesso")
    public void testProcessarConsultaSuccess() throws Exception {
        // Arrange
        String mensagem = "{\"paciente\":\"Paciente 1\", \"medico\":\"Dr. A\", \"enfermeiro\":\"Enfermeiro X\", \"dataHora\":\"2023-04-01T10:00:00\"}";

        // Act
        historyService.processarConsulta(mensagem);

        // Assert
        verify(historyRepository, times(1)).save(any(History.class));
    }

    //todo
/*    @Test
    @DisplayName("Deve não salvar consulta se já existir")
    public void testProcessarConsultaAlreadyExists() throws Exception {
        // Arrange
        String mensagem = "{\"paciente\":\"Paciente 1\", \"medico\":\"Dr. A\", \"enfermeiro\":\"Enfermeiro X\", \"dataHora\":\"2023-04-01T10:00:00\"}";
        HistoryDTO historyDTO = objectMapper.readValue(mensagem, HistoryDTO.class);
        String uniqueKey = historyDTO.getPaciente() + "_" + historyDTO.getDataHora();

        // Simula que a consulta já existe
        when(historyRepository.findByUniqueKey(uniqueKey)).thenReturn(Optional.of(new History()));

        // Act
        historyService.processarConsulta(mensagem);

        // Assert
        verify(historyRepository, never()).save(any(History.class));
    }*/


    @Test
    @DisplayName("Deve retornar consultas por ID do paciente")
    public void testGetConsultationsByPatientId() {
        // Arrange
        String paciente = "Paciente 1";
        History history = new History();
        history.setPaciente(paciente);
        when(historyRepository.findByPaciente(paciente)).thenReturn(Arrays.asList(history));

        // Act
        List<History> result = historyService.getConsultationsByPatientId(paciente);

        // Assert
        assertEquals(1, result.size());
        assertEquals(paciente, result.get(0).getPaciente());
    }

    @Test
    @DisplayName("Deve salvar uma nova consulta")
    public void testSaveHistory() {
        // Arrange
        History history = new History();
        history.setPaciente("Paciente 1");
        when(historyRepository.save(history)).thenReturn(history);

        // Act
        History result = historyService.saveHistory(history);

        // Assert
        assertNotNull(result);
        assertEquals("Paciente 1", result.getPaciente());
    }

    @Test
    @DisplayName("Deve retornar todas as consultas")
    public void testGetAllHistories() {
        // Arrange
        History history1 = new History();
        history1.setPaciente("Paciente 1");
        History history2 = new History();
        history2.setPaciente("Paciente 2");
        when(historyRepository.findAll()).thenReturn(Arrays.asList(history1, history2));

        // Act
        List<History> result = historyService.getAllHistories();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Deve retornar histórico de consultas por médico")
    public void testGetHistoryByMedico() {
        // Arrange
        String medico = "Dr. A";
        History history = new History();
        history.setMedico(medico);
        when(historyRepository.findByMedico(medico)).thenReturn(Arrays.asList(history));

        // Act
        List<History> result = historyService.getHistoryByMedico(medico);

        // Assert
        assertEquals(1, result.size());
        assertEquals(medico, result.get(0).getMedico());
    }

    @Test
    @DisplayName("Deve retornar histórico de consultas por enfermeiro")
    public void testGetHistoryByEnfermeiro() {
        // Arrange
        String enfermeiro = "Enfermeiro X";
        History history = new History();
        history.setEnfermeiro(enfermeiro);
        when(historyRepository.findByEnfermeiro(enfermeiro)).thenReturn(Arrays.asList(history));

        // Act
        List<History> result = historyService.getHistoryByEnfermeiro(enfermeiro);

        // Assert
        assertEquals(1, result.size());
        assertEquals(enfermeiro, result.get(0).getEnfermeiro());
    }
}