package br.com.machado.gestaovagas.modules.candidate.controllers;

import br.com.machado.gestaovagas.modules.candidate.CandidateEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @PostMapping("/")
    public void create(@RequestBody CandidateEntity candidateEntity) {
        System.out.println("Candidato:");
        System.out.println(candidateEntity);
    }
}
