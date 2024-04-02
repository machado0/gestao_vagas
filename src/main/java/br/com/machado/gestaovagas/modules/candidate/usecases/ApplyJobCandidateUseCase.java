package br.com.machado.gestaovagas.modules.candidate.usecases;

import br.com.machado.gestaovagas.modules.candidate.entities.ApplyJobEntity;
import br.com.machado.gestaovagas.modules.candidate.repositories.ApplyJobRepository;
import br.com.machado.gestaovagas.modules.candidate.repositories.CandidateRepository;
import br.com.machado.gestaovagas.modules.company.repositories.JobRepository;
import br.com.machado.gestaovagas.modules.exceptions.JobNotFoundException;
import br.com.machado.gestaovagas.modules.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID candidateId, UUID jobId) {
        candidateRepository.findById(candidateId)
                .orElseThrow(UserNotFoundException::new);

        jobRepository.findById(jobId)
                .orElseThrow(JobNotFoundException::new);

        return applyJobRepository.save(ApplyJobEntity.builder()
                .candidateId(candidateId)
                .jobId(jobId)
                .build());
    }
}
