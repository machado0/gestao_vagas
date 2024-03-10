package br.com.machado.gestaovagas.modules.company.usecases;

import br.com.machado.gestaovagas.modules.company.entities.CompanyEntity;
import br.com.machado.gestaovagas.modules.company.repositories.CompanyRepository;
import br.com.machado.gestaovagas.modules.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        companyRepository.findByEmailOrUsername(companyEntity.getEmail(), companyEntity.getUsername())
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException();
                });
        return companyRepository.save(companyEntity);
    }

}
