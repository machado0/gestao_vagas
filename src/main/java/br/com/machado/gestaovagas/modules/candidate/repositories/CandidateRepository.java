package br.com.machado.gestaovagas.modules.candidate.repositories;

import br.com.machado.gestaovagas.modules.candidate.entities.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {

    Optional<CandidateEntity> findByEmailOrUsername(String email, String username);
    Optional<CandidateEntity> findByUsername(String username);

}
