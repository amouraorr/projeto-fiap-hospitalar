package com.fiap.hospitalar.historico.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

import java.time.LocalDateTime;

@SchemaMapping(typeName = "History")
@Data
public class HistoryDTO {

    private Long id;
    private String paciente;
    private String medico;
    private String enfermeiro;
    //private String status; // Por exemplo, status da consulta

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") // Formato correto
    private LocalDateTime dataHora;

}