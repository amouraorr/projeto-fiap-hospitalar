package com.fiap.hospitalar.historico.repository;

import com.fiap.hospitalar.historico.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByPaciente(String paciente);

    List<History> findByMedico(String medico);

    List<History> findByEnfermeiro(String enfermeiro);

    Optional findByUniqueKey(String uniqueKey);
}