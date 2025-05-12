package com.fiap.hospitalar.agendamento.controller;

import com.fiap.hospitalar.agendamento.dto.request.MedicalAppointmentRequestDTO;
import com.fiap.hospitalar.agendamento.dto.response.MedicalAppointmentResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.web.client.TestRestTemplate;

import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MedicalAppointmentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/appointments";
    }

    @Test
    @DisplayName("Teste de criação e consulta de consulta médica com sucesso")
    public void testCreateAndGetAppointment() {
        String baseUrl = getBaseUrl();

        MedicalAppointmentRequestDTO requestDto = new MedicalAppointmentRequestDTO();
        requestDto.setPaciente("Patient A");
        requestDto.setMedico("Dr. Smith");
        requestDto.setEnfermeiro("Nurse Joy");
        requestDto.setDataHora(LocalDateTime.of(2024, 4, 29, 10, 0, 0));

        ResponseEntity<MedicalAppointmentResponseDTO> postResponse =
                restTemplate.postForEntity(baseUrl, requestDto, MedicalAppointmentResponseDTO.class);

        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode(), "Expected HTTP status CREATED");
        MedicalAppointmentResponseDTO createdAppointment = postResponse.getBody();
        assertNotNull(createdAppointment, "Created appointment should not be null");
        assertNotNull(createdAppointment.getId(), "Appointment ID should not be null");
        assertEquals("Patient A", createdAppointment.getPaciente());
        assertEquals("Dr. Smith", createdAppointment.getMedico());
        assertEquals("Nurse Joy", createdAppointment.getEnfermeiro());

        // Recupera a consulta pelo ID
        String getUrl = baseUrl + "/" + createdAppointment.getId();
        ResponseEntity<MedicalAppointmentResponseDTO> getResponse =
                restTemplate.getForEntity(getUrl, MedicalAppointmentResponseDTO.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode(), "Expected HTTP status OK");
        MedicalAppointmentResponseDTO appointmentFromGet = getResponse.getBody();
        assertNotNull(appointmentFromGet, "Appointment from GET should not be null");
        assertEquals(createdAppointment.getId(), appointmentFromGet.getId());
        assertEquals("Patient A", appointmentFromGet.getPaciente());
        assertEquals("Dr. Smith", appointmentFromGet.getMedico());
    }

    @Test
    @DisplayName("Deve atualizar um agendamento existente")
    public void testUpdateAppointment() {
        String baseUrl = getBaseUrl();

        MedicalAppointmentRequestDTO requestDto = new MedicalAppointmentRequestDTO();
        requestDto.setPaciente("Patient B");
        requestDto.setMedico("Dr. Brown");
        requestDto.setEnfermeiro("Nurse May");
        requestDto.setDataHora(LocalDateTime.of(2024, 4, 29, 11, 0, 0));

        ResponseEntity<MedicalAppointmentResponseDTO> postResponse =
                restTemplate.postForEntity(baseUrl, requestDto, MedicalAppointmentResponseDTO.class);
        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
        Long id = postResponse.getBody().getId();
        assertNotNull(id, "ID must be provided for update");

        MedicalAppointmentRequestDTO updateDto = new MedicalAppointmentRequestDTO();
        updateDto.setPaciente("Patient B Updated");
        updateDto.setMedico("Dr. Brown Updated");
        updateDto.setEnfermeiro("Nurse May Updated");
        updateDto.setDataHora(LocalDateTime.of(2024, 5, 1, 12, 0, 0));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MedicalAppointmentRequestDTO> requestEntity = new HttpEntity<>(updateDto, headers);

        ResponseEntity<MedicalAppointmentResponseDTO> updateResponse =
                restTemplate.exchange(baseUrl + "/" + id, HttpMethod.PUT, requestEntity, MedicalAppointmentResponseDTO.class);

        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
        MedicalAppointmentResponseDTO updatedAppointment = updateResponse.getBody();
        assertNotNull(updatedAppointment, "Updated appointment should not be null");
        assertEquals("Patient B Updated", updatedAppointment.getPaciente());
        assertEquals("Dr. Brown Updated", updatedAppointment.getMedico());
        assertEquals("Nurse May Updated", updatedAppointment.getEnfermeiro());
    }

    @Test
    @DisplayName("Teste de todas as consultas médicas")
    public void testGetAllAppointments() {
        String baseUrl = getBaseUrl();

        ResponseEntity<MedicalAppointmentResponseDTO[]> response =
                restTemplate.getForEntity(baseUrl, MedicalAppointmentResponseDTO[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        MedicalAppointmentResponseDTO[] appointments = response.getBody();
        assertNotNull(appointments, "Appointments list should not be null");

    }

    /*@Test
    @DisplayName("Deve excluir um agendamento e retornar NOT_FOUND ao buscá-lo")
    public void testDeleteAppointment() {
        String baseUrl = getBaseUrl();

        MedicalAppointmentRequestDTO requestDto = new MedicalAppointmentRequestDTO();
        requestDto.setPaciente("Patient C");
        requestDto.setMedico("Dr. Green");
        requestDto.setEnfermeiro("Nurse Lee");
        requestDto.setDataHora(LocalDateTime.of(2024, 4, 29, 12, 0, 0));

        ResponseEntity<MedicalAppointmentResponseDTO> postResponse =
                restTemplate.postForEntity(baseUrl, requestDto, MedicalAppointmentResponseDTO.class);
        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
        Long id = postResponse.getBody().getId();
        assertNotNull(id, "ID must be provided for deletion");

        ResponseEntity<Void> deleteResponse =
                restTemplate.exchange(baseUrl + "/" + id, HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());

        ResponseEntity<MedicalAppointmentResponseDTO> getResponseAfterDelete =
                restTemplate.getForEntity(baseUrl + "/" + id, MedicalAppointmentResponseDTO.class);
        assertEquals(HttpStatus.NOT_FOUND, getResponseAfterDelete.getStatusCode());
    }
*/
}