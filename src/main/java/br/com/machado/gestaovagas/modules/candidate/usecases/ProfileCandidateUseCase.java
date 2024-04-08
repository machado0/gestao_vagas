package br.com.machado.gestaovagas.modules.candidate.usecases;

import br.com.machado.gestaovagas.exceptions.UserNotFoundException;
import br.com.machado.gestaovagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.machado.gestaovagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID idCandidate) {
        var candidate =  candidateRepository.findById(idCandidate)
                .orElseThrow(() -> {throw new UserNotFoundException();});

        return ProfileCandidateResponseDTO.builder()
                .id(candidate.getId())
                .description(candidate.getDescription())
                .email(candidate.getEmail())
                .name(candidate.getName())
                .username(candidate.getUsername())
                .build();
    }

}
