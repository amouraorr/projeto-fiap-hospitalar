package com.fiap.hospitalar.notificacoes.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private JavaMailSender emailSender;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Deve processar mensagem recebida com sucesso")
    public void testListenSuccess() throws Exception {
        // Arrange
        String message = "{\"paciente\":\"Paciente 1\", \"medico\":\"Dr. A\", \"enfermeiro\":\"Enfermeiro X\", \"dataHora\":\"2023-04-01T10:00:00\"}";

        // Act
        notificationService.listen(message);

        // Assert
        // Aqui você pode verificar se o logger foi chamado ou se a notificação foi enviada
        // Como o logger não pode ser verificado diretamente, você pode usar um framework de logging que suporte isso
    }

    @Test
    @DisplayName("Deve lidar com erro ao processar mensagem JSON")
    public void testListenJsonProcessingException() {
        // Arrange
        String message = "mensagem inválida"; // Mensagem que não pode ser processada

        // Act
        notificationService.listen(message);

        // Assert
        // Verifique se o logger registrou o erro
    }

    @Test
    @DisplayName("Deve enviar email com sucesso")
    public void testSendEmailSuccess() {
        // Arrange
        String to = "test@example.com";
        String subject = "Assunto";
        String text = "Corpo do email";

        // Act
        notificationService.sendEmail(to, subject, text);

        // Assert
        ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(emailSender, times(1)).send(messageCaptor.capture());

        SimpleMailMessage capturedMessage = messageCaptor.getValue();
        assertEquals(to, capturedMessage.getTo()[0]);
        assertEquals(subject, capturedMessage.getSubject());
        assertEquals(text, capturedMessage.getText());
    }
}