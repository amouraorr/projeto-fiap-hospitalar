package com.fiap.hospitalar.agendamento.service;

import com.fiap.hospitalar.agendamento.dto.response.MedicalAppointmentResponseDTO;
import com.fiap.hospitalar.agendamento.message.KafkaProducerMessage;
import com.fiap.hospitalar.agendamento.model.MedicalAppointment;
import com.fiap.hospitalar.agendamento.repository.MedicalAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalAppointmentService {

    @Autowired
    private MedicalAppointmentRepository appointmentRepository;

    @Autowired
    private final KafkaProducerMessage kafkaMessageService;

    public MedicalAppointmentService(KafkaProducerMessage kafkaMessageService) {
        this.kafkaMessageService = kafkaMessageService;
    }

    public List<MedicalAppointment> findAll() {
        return appointmentRepository.findAll();
    }

    public MedicalAppointment findById(Long id) {
        Optional<MedicalAppointment> appointment = appointmentRepository.findById(id);
        return appointment.orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public MedicalAppointment save(MedicalAppointment appointmentRequestDTO) {
        MedicalAppointment appointment = new MedicalAppointment();
        appointment.setPaciente(appointmentRequestDTO.getPaciente());
        appointment.setMedico(appointmentRequestDTO.getMedico());
        appointment.setEnfermeiro(appointmentRequestDTO.getEnfermeiro());
        appointment.setDataHora(appointmentRequestDTO.getDataHora());

        MedicalAppointment createdAppointment = appointmentRepository.save(appointment);

        MedicalAppointmentResponseDTO responseDTO = new MedicalAppointmentResponseDTO();
        responseDTO.setId(createdAppointment.getId());
        responseDTO.setPaciente(createdAppointment.getPaciente());
        responseDTO.setMedico(createdAppointment.getMedico());
        responseDTO.setEnfermeiro(createdAppointment.getEnfermeiro());
        responseDTO.setDataHora(createdAppointment.getDataHora());

        kafkaMessageService.sendMessage(responseDTO);
        return createdAppointment;
    }

    public MedicalAppointment update(MedicalAppointment appointment) {

        if (!appointmentRepository.existsById(appointment.getId())) {
            throw new RuntimeException("Appointment not found");
        }
        return appointmentRepository.save(appointment);
    }

    public void delete(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new RuntimeException("Appointment not found");
        }
        appointmentRepository.deleteById(id);
    }
}