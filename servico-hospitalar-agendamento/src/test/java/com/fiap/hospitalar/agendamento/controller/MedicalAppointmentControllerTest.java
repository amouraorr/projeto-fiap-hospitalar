package com.fiap.hospitalar.agendamento.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.hospitalar.agendamento.dto.request.MedicalAppointmentRequestDTO;
import com.fiap.hospitalar.agendamento.dto.response.MedicalAppointmentResponseDTO;
import com.fiap.hospitalar.agendamento.mapper.MedicalAppointmentMapper;
import com.fiap.hospitalar.agendamento.model.MedicalAppointment;
import com.fiap.hospitalar.agendamento.service.MedicalAppointmentService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class MedicalAppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalAppointmentService appointmentService;

    @MockBean
    private MedicalAppointmentMapper medicalAppointmentMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    @DisplayName("Deve criar uma nova consulta médica com sucesso")
    public void testCreateAppointmentSuccess() throws Exception {

        MedicalAppointmentRequestDTO requestDTO = new MedicalAppointmentRequestDTO();

        MedicalAppointment appointment = new MedicalAppointment();

        when(medicalAppointmentMapper.toEntity(requestDTO)).thenReturn(appointment);
        when(appointmentService.save(any(MedicalAppointment.class))).thenReturn(appointment);
        when(medicalAppointmentMapper.toResponseDTO(appointment)).thenReturn(new MedicalAppointmentResponseDTO());

        mockMvc.perform(post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Deve atualizar uma consulta médica com sucesso")
    public void testUpdateAppointmentSuccess() throws Exception {

        Long appointmentId = 1L;
        MedicalAppointmentRequestDTO requestDTO = new MedicalAppointmentRequestDTO();

        MedicalAppointment appointment = new MedicalAppointment();
        appointment.setId(appointmentId);

        when(medicalAppointmentMapper.toEntity(requestDTO)).thenReturn(appointment);
        when(appointmentService.update(any(MedicalAppointment.class))).thenReturn(appointment);
        when(medicalAppointmentMapper.toResponseDTO(appointment)).thenReturn(new MedicalAppointmentResponseDTO());

        mockMvc.perform(put("/appointments/" + appointmentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve obter todas as consultas médicas com sucesso")
    public void testGetAllAppointments() throws Exception {

        List<MedicalAppointment> appointments = Collections.singletonList(new MedicalAppointment());
        when(appointmentService.findAll()).thenReturn(appointments);
        when(medicalAppointmentMapper.toResponseDTO(any(MedicalAppointment.class))).thenReturn(new MedicalAppointmentResponseDTO());

        mockMvc.perform(get("/appointments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve obter uma consulta médica por ID com sucesso")
    public void testGetAppointmentById() throws Exception {

        Long appointmentId = 1L;
        MedicalAppointment appointment = new MedicalAppointment();
        appointment.setId(appointmentId);
        when(appointmentService.findById(appointmentId)).thenReturn(appointment);
        when(medicalAppointmentMapper.toResponseDTO(appointment)).thenReturn(new MedicalAppointmentResponseDTO());

        mockMvc.perform(get("/appointments/" + appointmentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

/*    @Test
    @DisplayName("Deve retornar 404 ao tentar excluir uma consulta médica inexistente")
    public void testDeleteAppointmentNotFound() throws Exception {
        // Arrange
        Long appointmentId = 999L; // ID que não existe
        doThrow(new ResourceNotFoundException("Consulta médica não encontrada")).when(appointmentService).delete(appointmentId);

        // Act
        mockMvc.perform(delete("/appointments/" + appointmentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()); // Verifica se o status é 404 Not Found
    }*/

}