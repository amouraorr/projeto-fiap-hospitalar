package com.fiap.hospitalar.notificacoes.dto.response;

import lombok.Data;

@Data
public class ConsultationResponseDTO {

    private Long id;
    private String paciente;
    private String medico;
    private String enfermeiro;
    private String dataHora;
    private String status;
}
