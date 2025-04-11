package com.fiap.hospitalar.agendamento.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MedicalAppointmentResponseDTO {

    private Long id;
    private String paciente;
    private String medico;
    private String enfermeiro;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") // Formato correto
    private LocalDateTime dataHora;
}
