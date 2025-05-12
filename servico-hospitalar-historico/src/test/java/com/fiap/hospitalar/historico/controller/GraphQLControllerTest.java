package com.fiap.hospitalar.historico.controller;

import com.fiap.hospitalar.historico.dto.HistoryDTO;
import com.fiap.hospitalar.historico.mapper.HistoryMapper;
import com.fiap.hospitalar.historico.model.History;
import com.fiap.hospitalar.historico.service.HistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GraphQLControllerTest {

    @Autowired
    private GraphQLController graphQLController;

    @MockBean
    private HistoryService historyService;

    @MockBean
    private HistoryMapper historyMapper;

    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(graphQLController).build();
    }

    @Test
    @DisplayName("Deve retornar histórico de consultas para um paciente")
    public void testGetPatientHistory() {

        String paciente = "Paciente 1";
        History history = new History();
        history.setPaciente(paciente);
        HistoryDTO historyDTO = new HistoryDTO();
        historyDTO.setPaciente(paciente);

        when(historyService.getConsultationsByPatientId(paciente)).thenReturn(Arrays.asList(history));
        when(historyMapper.historyToHistoryDTO(history)).thenReturn(historyDTO); // Usando o mock do HistoryMapper

        List<HistoryDTO> result = graphQLController.getPatientHistory(paciente);

        assertEquals(1, result.size());
        assertEquals(paciente, result.get(0).getPaciente());
    }

    @Test
    @DisplayName("Deve adicionar um histórico com sucesso")
    public void testAddHistory() {

        String paciente = "Paciente 1";
        String medico = "Dr. A";
        String enfermeiro = "Enfermeiro X";
        String dataHora = LocalDateTime.now().toString();

        History history = new History();
        history.setPaciente(paciente);
        history.setMedico(medico);
        history.setEnfermeiro(enfermeiro);
        history.setDataHora(LocalDateTime.parse(dataHora));

        HistoryDTO savedHistoryDTO = new HistoryDTO();
        savedHistoryDTO.setPaciente(paciente);

        when(historyService.saveHistory(history)).thenReturn(history);
        when(historyMapper.historyToHistoryDTO(history)).thenReturn(savedHistoryDTO); // Usando o mock do HistoryMapper

        HistoryDTO result = graphQLController.addHistory(paciente, medico, enfermeiro, dataHora);

        assertNotNull(result);
        assertEquals(paciente, result.getPaciente());
    }

    @Test
    @DisplayName("Deve retornar todas as consultas")
    public void testGetAllHistories() {

        History history1 = new History();
        history1.setPaciente("Paciente 1");
        History history2 = new History();
        history2.setPaciente("Paciente 2");

        when(historyService.getAllHistories()).thenReturn(Arrays.asList(history1, history2));
        when(historyMapper.historyToHistoryDTO(history1)).thenReturn(new HistoryDTO());
        when(historyMapper.historyToHistoryDTO(history2)).thenReturn(new HistoryDTO());

        List<HistoryDTO> result = graphQLController.getAllHistories();

        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Deve retornar histórico de consultas por médico")
    public void testGetHistoriesByMedico() {

        String medico = "Dr. A";
        History history = new History();
        history.setMedico(medico);

        when(historyService.getHistoryByMedico(medico)).thenReturn(Arrays.asList(history));
        when(historyMapper.historyToHistoryDTO(history)).thenReturn(new HistoryDTO());

        List<HistoryDTO> result = graphQLController.getHistoriesByMedico(medico);

        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Deve retornar histórico de consultas por enfermeiro")
    public void testGetHistoryByEnfermeiro() {
        // Arrange
        String enfermeiro = "Enfermeiro X";
        History history = new History();
        history.setEnfermeiro(enfermeiro);

        when(historyService.getHistoryByEnfermeiro(enfermeiro)).thenReturn(Arrays.asList(history));
        when(historyMapper.historyToHistoryDTO(history)).thenReturn(new HistoryDTO());

        // Act
        List<HistoryDTO> result = graphQLController.getHistoryByEnfermeiro(enfermeiro);

        // Assert
        assertEquals(1, result.size());
    }
}