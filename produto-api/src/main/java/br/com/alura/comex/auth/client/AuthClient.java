package br.com.alura.comex.auth.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class AuthClient {
    private final RestClient client;

    public AuthClient(RestClient.Builder builder,
                      @Value("${auth.base-url}") String baseUrl) {
        this.client = builder.baseUrl(baseUrl).build();
    }

    public TokenValidationResponse validateToken(String bearerToken) {
        return client.post()
                .uri("/login/token/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .body(new TokenValidationRequest(bearerToken))
                .retrieve()
                .body(TokenValidationResponse.class);
    }
}
