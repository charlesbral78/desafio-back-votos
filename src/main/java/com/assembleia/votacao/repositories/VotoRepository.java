package com.assembleia.votacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assembleia.votacao.models.Voto;

import java.util.List;
import java.util.Optional;

public interface VotoRepository extends JpaRepository<Voto, Long>{

    Optional<Voto> findByIdAssociadoEqualsAndSessaoVotacaoIdEquals(Long idAssociado, Long SessaoVotacaoId);

    List<Voto> findAllBySessaoVotacaoId(Long idSessaoVotacao);

}
