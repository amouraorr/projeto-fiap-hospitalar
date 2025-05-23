package com.fiap.hospitalar.historico.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "historico")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uniqueKey; // Por exemplo, paciente + dataHora (ou outro critério)
    private String paciente;
    private String medico;
    private String enfermeiro;
    private LocalDateTime dataHora;
}
