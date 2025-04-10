package com.fiap.hospitalar.historico.resolver;

import com.fiap.hospitalar.historico.model.History;
import com.fiap.hospitalar.historico.service.HistoryService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
@Component
public class HistoryResolver implements GraphQLQueryResolver {

    @Autowired
    private HistoryService historyService;

    public List<History> getPatientHistory(String paciente) {
        return historyService.getConsultationsByPatientId(paciente);
    }

    public History addHistory(String paciente, String medico, String enfermeiro, String dataHora) {
        History history = new History();
        history.setPaciente(paciente);
        history.setMedico(medico);
        history.setEnfermeiro(enfermeiro);
        history.setDataHora(LocalDateTime.parse(dataHora));
        return historyService.saveHistory(history);
    }
}
