package com.assembleia.votacao.services;

import com.assembleia.votacao.mapper.VotoMapper;
import com.assembleia.votacao.models.SessaoVotacao;
import com.assembleia.votacao.models.Voto;
import com.assembleia.votacao.dtos.VotoDTO;
import com.assembleia.votacao.repositories.SessaoVotacaoRepository;
import com.assembleia.votacao.repositories.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VotoService {

    @Autowired
    VotoRepository votorepository;

    @Autowired
    SessaoVotacaoRepository sessaoVotacaoRepository;

    public Voto salvarVoto(VotoDTO votoDTO) {

        Optional<SessaoVotacao> sessaoVotacao = sessaoVotacaoRepository.findById(votoDTO.getId_sessao_votacao());
            if(sessaoVotacao.isEmpty())
                throw new IllegalArgumentException("Sessão não existe");
            else{
                 LocalDateTime  dataAtual = LocalDateTime.now();
                   if (sessaoVotacao.get().getDataFim().isBefore(dataAtual) || sessaoVotacao.get().getDataInicio().isAfter(dataAtual))
                       throw new IllegalArgumentException("Sessão fechada para votos!");
                   else{
                       Optional<Voto> votoProcurado = votorepository.findByIdAssociadoEqualsAndSessaoVotacaoIdEquals(votoDTO.getIdAssociado(), votoDTO.getId_sessao_votacao());
                       if (votoProcurado.isPresent())
                           throw new IllegalArgumentException("Voto já registrado na sessão!");
                       else {
                        return votorepository.save(VotoMapper.dtoParaVoto(votoDTO, sessaoVotacao.get()));
                    }
                }
            }
    }
}

