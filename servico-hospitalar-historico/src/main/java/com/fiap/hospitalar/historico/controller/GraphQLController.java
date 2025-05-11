package com.fiap.hospitalar.historico.controller;

import com.fiap.hospitalar.historico.dto.HistoryDTO;
import com.fiap.hospitalar.historico.mapper.HistoryMapper;
import com.fiap.hospitalar.historico.model.History;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Controller
public class GraphQLController {

    @QueryMapping
    public List<HistoryDTO> getPatientHistory(@Argument String paciente) {

        History history = new History();
        history.setId(1L);
        history.setPaciente(paciente);
        history.setMedico("Dr. Silva");
        history.setEnfermeiro("Enf. Souza");
        history.setDataHora(LocalDateTime.parse("2025-05-09T18:30:00"));

        HistoryDTO dto = HistoryMapper.INSTANCE.historyToHistoryDTO(history);

        return Collections.singletonList(dto);

    }
}
