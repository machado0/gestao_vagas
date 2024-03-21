package br.com.machado.gestaovagas.modules.candidate.usecases;

import br.com.machado.gestaovagas.modules.company.entities.JobEntity;
import br.com.machado.gestaovagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllJobsByFilterUseCase {

    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute(String filter) {
        return jobRepository.findByDescriptionContainingIgnoreCase(filter);
    }

}
