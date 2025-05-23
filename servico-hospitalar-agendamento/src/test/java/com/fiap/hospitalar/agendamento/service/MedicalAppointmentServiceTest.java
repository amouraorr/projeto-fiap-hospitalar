package com.fiap.hospitalar.agendamento.service;

import com.fiap.hospitalar.agendamento.message.KafkaProducerMessage;
import com.fiap.hospitalar.agendamento.model.MedicalAppointment;
import com.fiap.hospitalar.agendamento.repository.MedicalAppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicalAppointmentServiceTest {

    @Mock
    private MedicalAppointmentRepository appointmentRepository;

    @Mock
    private KafkaProducerMessage kafkaMessageService;

    @InjectMocks
    private MedicalAppointmentService appointmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve salvar uma nova consulta médica")
    public void testSave() {

        MedicalAppointment appointmentRequestDTO = new MedicalAppointment();
        appointmentRequestDTO.setPaciente("Paciente 1");
        appointmentRequestDTO.setMedico("Dr. A");
        appointmentRequestDTO.setEnfermeiro("Enfermeiro X");
        appointmentRequestDTO.setDataHora(LocalDateTime.now());

        MedicalAppointment createdAppointment = new MedicalAppointment();
        createdAppointment.setId(1L);
        createdAppointment.setPaciente(appointmentRequestDTO.getPaciente());
        createdAppointment.setMedico(appointmentRequestDTO.getMedico());
        createdAppointment.setEnfermeiro(appointmentRequestDTO.getEnfermeiro());
        createdAppointment.setDataHora(appointmentRequestDTO.getDataHora());

        when(appointmentRepository.save(any(MedicalAppointment.class))).thenReturn(createdAppointment);

        MedicalAppointment result = appointmentService.save(appointmentRequestDTO);

        assertEquals(createdAppointment, result, "Deve retornar a consulta criada");
        verify(appointmentRepository, times(1)).save(appointmentRequestDTO);

    }


    @Test
    @DisplayName("Deve retornar todas as consultas")
    public void testFindAll() {

        MedicalAppointment appointment1 = new MedicalAppointment();
        appointment1.setId(1L);
        appointment1.setPaciente("Paciente 1");
        appointment1.setMedico("Dr. A");
        appointment1.setEnfermeiro("Enfermeiro X");
        appointment1.setDataHora(LocalDateTime.now());

        MedicalAppointment appointment2 = new MedicalAppointment();
        appointment2.setId(2L);
        appointment2.setPaciente("Paciente 2");
        appointment2.setMedico("Dr. B");
        appointment2.setEnfermeiro("Enfermeiro Y");
        appointment2.setDataHora(LocalDateTime.now());

        when(appointmentRepository.findAll()).thenReturn(List.of(appointment1, appointment2));

        List<MedicalAppointment> result = appointmentService.findAll();

        assertEquals(2, result.size(), "Deve retornar 2 consultas");
    }

    @Test
    @DisplayName("Deve retornar uma consulta pelo ID")
    public void testFindById() {

        MedicalAppointment appointment = new MedicalAppointment();
        appointment.setId(1L);
        appointment.setPaciente("Paciente 1");
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));

        MedicalAppointment result = appointmentService.findById(1L);

        assertEquals(appointment, result, "Deve retornar a consulta correspondente ao ID");
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar consulta com ID inexistente")
    public void testFindByIdNotFound() {

        when(appointmentRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            appointmentService.findById(1L);
        });
        assertEquals("Appointment not found", exception.getMessage());
    }


    @Test
    @DisplayName("Deve lançar exceção ao atualizar consulta inexistente")
    public void testUpdateNotFound() {

        MedicalAppointment appointment = new MedicalAppointment();
        appointment.setId(1L);

        when(appointmentRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            appointmentService.update(appointment);
        });
        assertEquals("Appointment not found", exception.getMessage());
        verify(appointmentRepository, never()).save(any(MedicalAppointment.class));
    }

    @Test
    @DisplayName("Deve atualizar uma consulta existente")
    public void testUpdate() {

        MedicalAppointment appointment = new MedicalAppointment();
        appointment.setId(1L);
        appointment.setPaciente("Paciente 1");
        appointment.setMedico("Dr. A");
        appointment.setEnfermeiro("Enfermeiro X");
        appointment.setDataHora(LocalDateTime.now());

        when(appointmentRepository.existsById(1L)).thenReturn(true);
        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        MedicalAppointment result = appointmentService.update(appointment);

        assertEquals(appointment, result, "Deve retornar a consulta atualizada");
        verify(appointmentRepository, times(1)).save(appointment);
    }

    @Test
    @DisplayName("Deve lançar exceção ao deletar consulta inexistente")
    public void testDeleteNotFound() {

        Long appointmentId = 1L;
        when(appointmentRepository.existsById(appointmentId)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            appointmentService.delete(appointmentId);
        });
        assertEquals("Appointment not found", exception.getMessage());
    }

    @Test
    @DisplayName("Deve deletar uma consulta existente")
    public void testDeleteSuccess() {

        Long appointmentId = 1L;
        when(appointmentRepository.existsById(appointmentId)).thenReturn(true);

        appointmentService.delete(appointmentId);

        verify(appointmentRepository, times(1)).deleteById(appointmentId);
    }
}