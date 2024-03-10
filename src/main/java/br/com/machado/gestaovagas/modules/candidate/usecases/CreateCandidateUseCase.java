package br.com.machado.gestaovagas.modules.candidate.usecases;

import br.com.machado.gestaovagas.modules.candidate.CandidateEntity;
import br.com.machado.gestaovagas.modules.candidate.repositories.CandidateRepository;
import br.com.machado.gestaovagas.modules.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        candidateRepository.findByEmailOrUsername(candidateEntity.getEmail(), candidateEntity.getUsername())
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException();
                });
        return candidateRepository.save(candidateEntity);
    }
}
