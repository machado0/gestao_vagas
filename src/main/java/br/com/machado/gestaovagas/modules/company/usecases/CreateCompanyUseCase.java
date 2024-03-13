package br.com.machado.gestaovagas.modules.company.usecases;

import br.com.machado.gestaovagas.modules.company.entities.CompanyEntity;
import br.com.machado.gestaovagas.modules.company.repositories.CompanyRepository;
import br.com.machado.gestaovagas.modules.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        companyRepository.findByEmailOrUsername(companyEntity.getEmail(), companyEntity.getUsername())
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException();
                });

        var password = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);
        return companyRepository.save(companyEntity);
    }

}
