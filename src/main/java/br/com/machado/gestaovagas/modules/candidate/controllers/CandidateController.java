package br.com.machado.gestaovagas.modules.candidate.controllers;

import br.com.machado.gestaovagas.modules.candidate.CandidateEntity;
import br.com.machado.gestaovagas.modules.candidate.CandidateRepository;
import br.com.machado.gestaovagas.modules.exceptions.UserAlreadyExistsException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    private CandidateRepository repository;

    @PostMapping("/")
    public CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity) {
        repository.findByEmailOrUsername(candidateEntity.getEmail(), candidateEntity.getUsername())
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException();
                });
        return repository.save(candidateEntity);
    }
}
