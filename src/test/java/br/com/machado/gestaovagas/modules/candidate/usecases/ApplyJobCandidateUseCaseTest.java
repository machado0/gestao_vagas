package br.com.machado.gestaovagas.modules.candidate.usecases;

import br.com.machado.gestaovagas.modules.candidate.entities.ApplyJobEntity;
import br.com.machado.gestaovagas.modules.candidate.entities.CandidateEntity;
import br.com.machado.gestaovagas.modules.candidate.repositories.ApplyJobRepository;
import br.com.machado.gestaovagas.modules.candidate.repositories.CandidateRepository;
import br.com.machado.gestaovagas.modules.company.entities.JobEntity;
import br.com.machado.gestaovagas.modules.company.repositories.JobRepository;
import br.com.machado.gestaovagas.exceptions.JobNotFoundException;
import br.com.machado.gestaovagas.exceptions.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase useCase;
    @Mock
    private CandidateRepository candidateRepository;
    @Mock
    private JobRepository jobRepository;
    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply to job when candidate not found")
    void should_not_be_able_to_apply_job_to_when_candidate_not_found() {
        try {
            useCase.execute(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should not be able to apply to job when job not found")
    void should_not_be_able_to_apply_to_job_when_job_not_found() {
        var candidateId = UUID.randomUUID();

        when(candidateRepository.findById(candidateId))
                .thenReturn(Optional.of(new CandidateEntity()));

        try {
            useCase.execute(candidateId, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should be able to apply to job")
    void should_be_able_to_apply_to_job() {
        var candidateId = UUID.randomUUID();
        var jobId = UUID.randomUUID();

        when(candidateRepository.findById(candidateId))
                .thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(jobId))
                .thenReturn(Optional.of(new JobEntity()));
        when(applyJobRepository.save(any()))
                .thenReturn(ApplyJobEntity.builder()
                        .id(UUID.randomUUID())
                        .build());

        var result = useCase.execute(candidateId, jobId);

        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());
    }

}
