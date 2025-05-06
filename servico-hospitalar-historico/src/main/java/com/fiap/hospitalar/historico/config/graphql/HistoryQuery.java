
package com.fiap.hospitalar.historico.config.graphql;

import com.fiap.hospitalar.historico.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoryQuery {

    @Autowired
    private HistoryRepository historyRepository;

    public List<com.fiap.hospitalar.historico.model.History> getHistories() {
        return historyRepository.findAll();
    }
}