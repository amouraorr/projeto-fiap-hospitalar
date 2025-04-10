package com.fiap.hospitalar.historico.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.hospitalar.historico.dto.HistoryDTO;
import com.fiap.hospitalar.historico.model.History;
import com.fiap.hospitalar.historico.repository.HistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoryService {

    private static final Logger logger = LoggerFactory.getLogger(HistoryService.class);

    @Autowired
    private HistoryRepository historyRepository;

    @KafkaListener(topics = "consultas-agendadas", groupId = "historico")
    public void processarConsulta(String mensagem) {
        logger.info("Recebendo mensagem do tópico 'consultas-agendadas': {}", mensagem);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            HistoryDTO historyDTO = objectMapper.readValue(mensagem, HistoryDTO.class);
            History history = new History();
            history.setPaciente(historyDTO.getPaciente());
            history.setMedico(historyDTO.getMedico());
            history.setEnfermeiro(historyDTO.getEnfermeiro());
            history.setDataHora(LocalDateTime.parse((CharSequence) historyDTO));
            //history.setDataHora(LocalDateTime.parse(historyDTO.getDataHora())); // Corrigido para usar o campo correto
            historyRepository.save(history);
            logger.info("Consulta processada e salva com sucesso: {}", history);
        } catch (JsonProcessingException e) {
            logger.error("Erro ao processar a mensagem JSON: {}", e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Erro inesperado ao processar a consulta: {}", e.getMessage(), e);
        }
    }

    // Método para obter consultas por paciente
    public List<History> getConsultationsByPatientId(String paciente) {
        return historyRepository.findByPaciente(paciente);
    }

    // Método para salvar um histórico
    public History saveHistory(History history) {

        return historyRepository.save(history);
    }
}
