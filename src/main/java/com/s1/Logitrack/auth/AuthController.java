package com.s1.Logitrack.auth;

import com.s1.Logitrack.config.JwtService;
import com.s1.Logitrack.Exception.BusinessRuleException;
import com.s1.Logitrack.model.Persona;
import com.s1.Logitrack.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final PersonaRepository personaRepository;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {

        Persona persona = personaRepository.findByEmail(request.email())
                .orElseThrow(() -> new BusinessRuleException("Credenciales inválidas"));

        if (!persona.getPassword().equals(request.password())) {
            throw new BusinessRuleException("Credenciales inválidas");
        }

        String token = jwtService.generateToken(persona.getEmail());
        return Map.of("token", token);
    }
}
