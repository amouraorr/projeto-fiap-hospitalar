package com.fiap.hospitalar.agendamento.repository;

import com.fiap.hospitalar.agendamento.model.MedicalAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalAppointmentRepository extends JpaRepository<MedicalAppointment, Long> {

    List<MedicalAppointment> findByMedico(String medico);

}