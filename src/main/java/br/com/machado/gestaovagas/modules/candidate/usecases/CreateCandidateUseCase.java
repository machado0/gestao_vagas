package br.com.machado.gestaovagas.modules.candidate.usecases;

import br.com.machado.gestaovagas.modules.candidate.entities.CandidateEntity;
import br.com.machado.gestaovagas.modules.candidate.repositories.CandidateRepository;
import br.com.machado.gestaovagas.modules.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        candidateRepository.findByEmailOrUsername(candidateEntity.getEmail(), candidateEntity.getUsername())
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException();
                });

        candidateEntity.setPassword(passwordEncoder.encode(candidateEntity.getPassword()));

        return candidateRepository.save(candidateEntity);
    }
}
