package com.fiap.hospitalar.notificacoes.dto.request;

import lombok.Data;

@Data
public class ConsultationRequestDTO {
    private String paciente;
    private String medico;
    private String enfermeiro;
    private String dataHora;
}
