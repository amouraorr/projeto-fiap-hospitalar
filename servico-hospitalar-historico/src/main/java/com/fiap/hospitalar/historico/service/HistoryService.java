package com.fiap.hospitalar.historico.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fiap.hospitalar.historico.dto.HistoryDTO;
import com.fiap.hospitalar.historico.model.History;
import com.fiap.hospitalar.historico.repository.HistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    private static final Logger logger = LoggerFactory.getLogger(HistoryService.class);

    @Autowired
    private HistoryRepository historyRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @KafkaListener(topics = "consultas-agendadas", groupId = "historico")
    public void processarConsulta(String mensagem) {
        logger.info("Recebendo mensagem do tópico 'consultas-agendadas': {}", mensagem);

        try {
            HistoryDTO historyDTO = objectMapper.readValue(mensagem, HistoryDTO.class);
            logger.info("Paciente recebido no Kafka: {}", historyDTO.getPaciente());
            logger.info("DataHora recebida: {}", historyDTO.getDataHora());
            // Crie o objeto History e preencha os campos corretamente:
            History history = new History();
            history.setPaciente(historyDTO.getPaciente());  // Certifique-se de que esse campo está sendo setado!
            history.setMedico(historyDTO.getMedico());
            history.setEnfermeiro(historyDTO.getEnfermeiro());
            history.setDataHora(historyDTO.getDataHora());
            historyRepository.save(history);
            logger.info("Consulta processada e salva com sucesso: {}", history);
        } catch (JsonProcessingException e) {
            logger.error("Erro ao processar a mensagem JSON: {}", e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Erro inesperado ao processar a consulta: {}", e.getMessage(), e);
        }
    }

    public List<History> getConsultationsByPatientId(String paciente) {
        return historyRepository.findByPaciente(paciente);
    }

    public History saveHistory(History history) {

        return historyRepository.save(history);
    }

    public List<History> getHistoryByPaciente(String paciente) {
        return historyRepository.findByPaciente(paciente);
    }

    public List<History> getAllHistories() {
        return historyRepository.findAll();
    }

    // Novo método para buscar por médico
    public List<History> getHistoryByMedico(String medico) {
        return historyRepository.findByMedico(medico);
    }

    // Novo método para buscar por enfermeiro:
    public List<History> getHistoryByEnfermeiro(String enfermeiro) {
        return historyRepository.findByEnfermeiro(enfermeiro);
    }
}
