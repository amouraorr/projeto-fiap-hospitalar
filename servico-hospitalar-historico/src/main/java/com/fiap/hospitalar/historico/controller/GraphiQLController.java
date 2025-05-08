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
                "<title>GraphiQL</title>" +
                "<link rel=\"stylesheet\" href=\"https://unpkg.com/graphiql/graphiql.css\" />" +
                "<script src=\"https://unpkg.com/react/umd/react.production.min.js\"></script>" +
                "<script src=\"https://unpkg.com/react-dom/umd/react-dom.production.min.js\"></script>" +
                "<script src=\"https://unpkg.com/graphiql/graphiql.min.js\"></script>" +
                "</head>" +
                "<body>" +
                "<div id=\"graphiql\">Loading...</div>" +
                "<script>" +
                "const graphQLFetcher = (graphQLParams) => fetch('/graphql', {" +
                "method: 'POST'," +
                "headers: {'Content-Type': 'application/json'}," +
                "body: JSON.stringify(graphQLParams)," +
                "}).then(response => response.json()).catch(error => console.error(error));" +
                "ReactDOM.render(React.createElement(GraphiQL, { fetcher: graphQLFetcher }), document.getElementById('graphiql'));" +
                "</script>" +
                "</body>" +
                "</html>";
    }

}
