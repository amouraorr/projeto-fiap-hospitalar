/*
package com.fiap.hospitalar.historico.config.graphql;


import com.fiap.hospitalar.historico.resolver.HistoryResolver;
import graphql.kickstart.tools.SchemaParser;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

@Configuration
public class GraphQLConfig {

    @Autowired
    private HistoryResolver historyResolver;

    @Bean
    public GraphQLSchema schema() {
        // Carregando o arquivo schema.graphqls
        InputStream inputStream = getClass().getResourceAsStream("/graphql/schema.graphqls");
        if (inputStream == null) {
            throw new RuntimeException("Arquivo schema.graphqls não encontrado.");
        }

        // Parseando o arquivo para criar o TypeDefinitionRegistry
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(inputStream);

        // Configurando o RuntimeWiring
        RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", builder -> builder
                        .dataFetcher("getPatientHistory", environment -> {
                            String paciente = environment.getArgument("paciente");
                            return historyResolver.getPatientHistory(paciente);
                        }))
                .type("Mutation", builder -> builder
                        .dataFetcher("addHistory", environment -> {
                            String paciente = environment.getArgument("paciente");
                            String medico = environment.getArgument("medico");
                            String enfermeiro = environment.getArgument("enfermeiro");
                            String dataHora = environment.getArgument("dataHora");
                            return historyResolver.addHistory(paciente, medico, enfermeiro, dataHora);
                        }))
                .build();

        // Construindo o GraphQLSchema com o TypeDefinitionRegistry e o RuntimeWiring
        return GraphQLSchema.newSchema(typeRegistry)
                .build(wiring);
    }
}
*/
