package br.com.alura.comex.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.comex.auth.client.AuthClient;
import br.com.alura.comex.auth.client.TokenValidationResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private AuthClient authClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);
        System.out.println("TOKEN RECUPERADO"+tokenJWT);
        if (tokenJWT == null) {
            filterChain.doFilter(request, response);
            return; 
        }
        TokenValidationResponse authResponse = authClient.validateToken(tokenJWT);
        System.out.println("RESPOSTA DO CLIENTE"+authResponse);
        Boolean valido = authResponse.valido();
        if (valido) {
            filterChain.doFilter(request, response);
        } else {
            sendError(response, HttpServletResponse.SC_FORBIDDEN, "Token inválido ou expirado");
        }
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        System.out.println("TENTANDO RECUPERA O TOKEN"+authorizationHeader);
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    private void sendError(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String body = String.format("{\"error\": \"%s\", \"status\": %d}", message, status);
        response.getWriter().write(body);
    }
}