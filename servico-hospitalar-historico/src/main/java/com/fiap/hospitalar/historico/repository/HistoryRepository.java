package com.fiap.hospitalar.historico.repository;

import com.fiap.hospitalar.historico.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByPaciente(String paciente);

    // Novo método para buscar por médico
    List<History> findByMedico(String medico);

    // Novo método para buscar por enfermeiro:
    List<History> findByEnfermeiro(String enfermeiro);
}