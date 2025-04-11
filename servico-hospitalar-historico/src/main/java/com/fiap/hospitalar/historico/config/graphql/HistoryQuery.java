
package com.fiap.hospitalar.historico.config.graphql;

import com.fiap.hospitalar.historico.repository.HistoryRepository;
import com.fiap.hospitalar.historico.resolver.HistoryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoryQuery extends HistoryResolver {

    @Autowired
    private HistoryRepository historyRepository;

    public List<com.fiap.hospitalar.historico.model.History> getHistories() {
        return historyRepository.findAll();
    }
}