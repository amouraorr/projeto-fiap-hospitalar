package com.fiap.hospitalar.notificacoes.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.hospitalar.notificacoes.dto.request.ConsultationRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);


    @Autowired
    private JavaMailSender emailSender;

    @KafkaListener(topics = "consultas-agendadas", groupId = "notification-group")
    public void listen(String message) {

        // Log da mensagem recebida
        logger.info("Mensagem recebida do tópico 'consultas-agendadas': {}", message);

        System.out.println("Mensagem recebida: " + message);

        ObjectMapper objectMapper = new ObjectMapper();

        logger.info("Tentando enviar notificações"+ message);
        try {
            // Tente converter a string JSON em um objeto
            ConsultationRequestDTO consultationRequestDTO = objectMapper.readValue(message, ConsultationRequestDTO.class);
            logger.info("Notificação enviada com sucesso: {}", consultationRequestDTO); // Log da consulta processada

        } catch (JsonProcessingException e) {

            // Tratar erro de processamento JSON
            logger.error("Erro ao processar a mensagem JSON: {}", e.getMessage(), e); // Log do erro

        } catch (Exception e) {
            logger.error("Erro inesperado: {}", e.getMessage(), e);
        }
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}