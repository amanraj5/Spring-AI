package com.tool.ToolCalling.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class WeatherTool {

    private RestClient restClient;

    public WeatherTool(RestClient restClient){
        this.restClient = restClient;
    }

    @Value("${weather.api.key}")
    private String weatherApiKey;

    @Tool(description = "Get weather information of given city.")
    public String getWeather(@ToolParam(description = "city of which we want to get the weather information") String city){

        String response = String.valueOf(restClient.get()
                .uri(
                        uriBuilder -> uriBuilder.path("/current.json")
                                .queryParam("key", weatherApiKey)
                                .queryParam("city", city)
                                .build()
                )
                .retrieve()
                .body(new ParameterizedTypeReference<Map<String, Object>>() {}));

        return response;
    }
}
