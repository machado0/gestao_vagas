package br.com.machado.gestaovagas.modules.company.usecases;

import br.com.machado.gestaovagas.modules.company.dto.AuthCompanyDTO;
import br.com.machado.gestaovagas.modules.company.dto.AuthCompanyResponseDTO;
import br.com.machado.gestaovagas.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = companyRepository.findByUsername(authCompanyDTO.getUsername())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Username/password incorrect");
                });

        if (!passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword())) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        return AuthCompanyResponseDTO.builder()
                .access_token(JWT.create()
                        .withIssuer("javagas")
                        .withSubject(company.getId().toString())
                        .withClaim("roles", List.of("COMPANY"))
                        .withExpiresAt(expiresIn)
                        .sign(algorithm))
                .expires_in(expiresIn.toEpochMilli())
                .build();
    }
}
