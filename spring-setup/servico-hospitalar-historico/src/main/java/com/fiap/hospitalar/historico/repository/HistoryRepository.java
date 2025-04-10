package com.fiap.hospitalar.historico.repository;

import com.fiap.hospitalar.historico.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByPaciente(String paciente);
}