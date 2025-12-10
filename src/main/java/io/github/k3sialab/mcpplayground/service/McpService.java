package io.github.k3sialab.mcpplayground.service;

import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class McpService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiUrl = "https://icanhazdadjoke.com";

    @McpTool(name = "hello-tool",
            description = "A tool that returns a hello message from the MCP server")
    public String getHello() {
        var greeting = "Hello from MCP server!";
        return greeting;
    }

    @McpTool(name = "funny-quote-tool",
            description = "Fetches a random funny quote or joke from an external API" )
    public String getFunnyQuote() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<McpService.JokeResponse> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    entity,
                    McpService.JokeResponse.class
            );

            McpService.JokeResponse jokeResponse = response.getBody();
            return jokeResponse != null ? jokeResponse.getJoke() : "Sorry, hardluck fetching a joke!... laugh later!";
        } catch (Exception e) {
            return "Sorry, couldn't fetch a joke right now. Try again later!";
        }
    }

    public static class JokeResponse {
        private String joke;

        public String getJoke() {
            return joke;
        }

        public void setJoke(String joke) {
            this.joke = joke;
        }
    }
}
