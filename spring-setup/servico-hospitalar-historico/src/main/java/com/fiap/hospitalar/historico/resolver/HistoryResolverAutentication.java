/*
package com.fiap.hospitalar.historico.resolver;

import com.fiap.hospitalar.historico.model.History;
import com.fiap.hospitalar.historico.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

*/
/* Ajustar a classe TODO **//*


public class HistoryResolverAutentication {

    @Autowired
    private HistoryRepository historyRepository;

    public List<History> getHistories() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        if (role.equals("ROLE_DOCTOR") || role.equals("ROLE_NURSE")) {
            return historyRepository.findAll();
        } else if (role.equals("ROLE_PATIENT")) {
            return historyRepository.findByPaciente(String.valueOf(Long.valueOf(username)));
        } else {
            throw new SecurityException("Access Denied");
        }
    }
}*/
