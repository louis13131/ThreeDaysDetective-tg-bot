package ru.urfu;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

public class ApiClient {
    private  final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public ApiClient(){
        httpClient = HttpClient.newHttpClient();
        objectMapper = new ObjectMapper();
    }

    private List<Name> fetchNames(String gender, int count) throws IOException, InterruptedException {
        String url = String.format("https://randomuser.me/api/?nat=us&inc=name&gender=%s&results=%d", gender, count);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            System.err.println(response.statusCode());
        }

        UserResponse userResponse = objectMapper.readValue(response.body(), UserResponse.class);

        return userResponse.results().stream()
                .map(UserResult::name)
                .collect(Collectors.toList());
    }

    public List<Name> findMale() throws IOException, InterruptedException {
        return fetchNames("Male", 4);
    }

    public List<Name> findFemale() throws IOException, InterruptedException {
        return fetchNames("Female", 2);
    }
}
