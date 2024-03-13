package br.com.machado.gestaovagas.modules.company.repositories;

import br.com.machado.gestaovagas.modules.company.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {

    Optional<CompanyEntity> findByEmailOrUsername(String email, String username);
    Optional<CompanyEntity> findByUsername(String username);
}
