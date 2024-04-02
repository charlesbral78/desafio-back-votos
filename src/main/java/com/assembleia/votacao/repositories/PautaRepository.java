package com.assembleia.votacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assembleia.votacao.models.Pauta;

import java.util.Optional;

public interface PautaRepository extends JpaRepository<Pauta, Long>{

    @Override
    Optional<Pauta> findById(Long id);

}
