/*
package com.fiap.hospitalar.historico.controller;

import com.fiap.hospitalar.historico.dto.HistoryDTO;
import com.fiap.hospitalar.historico.mapper.HistoryMapper;
import com.fiap.hospitalar.historico.model.History;
import com.fiap.hospitalar.historico.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @QueryMapping
    public List<HistoryDTO> getPatientHistory(@Argument String paciente) {
        List<History> histories = historyService.getConsultationsByPatientId(paciente);
        return histories.stream()
                .map(HistoryMapper.INSTANCE::historyToHistoryDTO)
                .collect(Collectors.toList());
    }

    @MutationMapping
    public HistoryDTO addHistory(@Argument String paciente, String medico, String enfermeiro, String dataHora) {
        History history = new History();
        history.setPaciente(paciente);
        history.setMedico(medico);
        history.setEnfermeiro(enfermeiro);
        history.setDataHora(LocalDateTime.parse(dataHora));
        History savedHistory = historyService.saveHistory(history);
        return HistoryMapper.INSTANCE.historyToHistoryDTO(savedHistory);
    }
}*/
