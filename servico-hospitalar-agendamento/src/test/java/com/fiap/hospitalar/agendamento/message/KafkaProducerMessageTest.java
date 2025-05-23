package com.fiap.hospitalar.agendamento.message;

import com.fiap.hospitalar.agendamento.dto.response.MedicalAppointmentResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

class KafkaProducerMessageTest {

    @Mock
    private KafkaTemplate<String, MedicalAppointmentResponseDTO> kafkaTemplate;

    @InjectMocks
    private KafkaProducerMessage kafkaProducerMessage;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve lançar exceção ao falhar ao enviar mensagem")
    public void testSendMessageFailure() throws Exception {

        MedicalAppointmentResponseDTO message = new MedicalAppointmentResponseDTO();
        message.setId(1L);
        message.setPaciente("Paciente 1");
        message.setMedico("Dr. A");
        message.setEnfermeiro("Enfermeiro X");
        message.setDataHora(LocalDateTime.now());

        when(kafkaTemplate.send(anyString(), any(MedicalAppointmentResponseDTO.class)))
                .thenThrow(new RuntimeException("Erro ao enviar mensagem"));

        kafkaProducerMessage.sendMessage(message);

        verify(kafkaTemplate, times(1)).send("consultas-agendadas", message);

    }
}
