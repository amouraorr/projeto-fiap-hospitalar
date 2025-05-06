package com.fiap.hospitalar.historico.mapper;

import com.fiap.hospitalar.historico.dto.HistoryDTO;
import com.fiap.hospitalar.historico.model.History;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HistoryMapper {
    HistoryMapper INSTANCE = Mappers.getMapper(HistoryMapper.class);

    HistoryDTO historyToHistoryDTO(History history);
    History historyDTOToHistory(HistoryDTO historyDTO);
}
