package br.com.machado.gestaovagas.modules.candidate;

import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.UUID;

@Data
public class CandidateEntity {

    private UUID id;

    @Email(message = "O campo deve conter um e-mail válido")
    private String name;
    private String username;
    private String email;
    private String password;
    private String description;
    private String curriculo;

}
