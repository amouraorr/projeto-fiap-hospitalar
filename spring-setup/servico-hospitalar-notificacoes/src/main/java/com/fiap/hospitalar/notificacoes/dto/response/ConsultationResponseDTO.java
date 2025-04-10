package com.fiap.hospitalar.notificacoes.dto.response;

import lombok.Data;

@Data
public class ConsultationResponseDTO {
    private String paciente;
    private String medico;
    private String enfermeiro;
    private String dataHora;
    private String status;   // Por exemplo, status da consulta
}
