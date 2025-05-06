package com.fiap.hospitalar.historico.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GraphiQLController {
    @GetMapping("/graphiql")
    @ResponseBody
    public String graphiql() {
        return "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset=utf-8/>" +
                "<title>GraphQL Playground</title>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no\"/>" +
                "<link rel=\"stylesheet\" href=\"//cdn.jsdelivr.net/npm/graphql-playground-react@1.8.10/build/static/css/index.css\" />" +
                "<link rel=\"shortcut icon\" href=\"//cdn.jsdelivr.net/npm/graphql-playground-react@1.8.10/build/favicon.png\" />" +
                "<script src=\"//cdn.jsdelivr.net/npm/graphql-playground-react@1.8.10/build/static/js/middleware.js\"></script>" +
                "</head>" +
                "<body>" +
                "<div id=\"root\"></div>" +
                "<script>window.addEventListener('DOMContentLoaded', function (event) {" +
                "GraphQLPlayground.init(document.getElementById('root'), {" +
                "endpoint: '/graphql'" +
                "})" +
                "})</script>" +
                "</body>" +
                "</html>";
    }
}