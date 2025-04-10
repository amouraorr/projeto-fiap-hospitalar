package com.fiap.hospitalar.historico.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
//@EqualsAndHashCode(exclude = "usuario")
@Entity
@Table(name = "historico")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String paciente;
    private String medico;
    private String enfermeiro;
    private LocalDateTime dataHora;
}
