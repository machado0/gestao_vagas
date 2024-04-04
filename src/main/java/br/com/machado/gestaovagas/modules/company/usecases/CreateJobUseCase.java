package br.com.machado.gestaovagas.modules.company.usecases;

import br.com.machado.gestaovagas.exceptions.CompanyNotFoundException;
import br.com.machado.gestaovagas.modules.company.entities.JobEntity;
import br.com.machado.gestaovagas.modules.company.repositories.CompanyRepository;
import br.com.machado.gestaovagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity) {
        companyRepository.findById(jobEntity.getCompanyId())
                .orElseThrow(() -> new CompanyNotFoundException("Company not found"));

        return jobRepository.save(jobEntity);
    }

}
