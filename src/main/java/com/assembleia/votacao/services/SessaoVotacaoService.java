package com.assembleia.votacao.services;

import com.assembleia.votacao.mapper.SessaoVotacaoMapper;
import com.assembleia.votacao.models.Pauta;
import com.assembleia.votacao.dtos.SessaoVotacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assembleia.votacao.models.SessaoVotacao;
import com.assembleia.votacao.repositories.SessaoVotacaoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SessaoVotacaoService {

    @Autowired
    SessaoVotacaoRepository sessaoVotacaoRepository;

    @Autowired
    PautaService pautaService;

    public SessaoVotacao salvarSessaoVotacao (SessaoVotacaoDTO sessaoVotacaoDTO) {

        Optional<SessaoVotacao> verificaSeExiste = sessaoVotacaoRepository.findByPautaId(sessaoVotacaoDTO.getIdPauta());

        if (verificaSeExiste.isPresent())
            throw new IllegalArgumentException("Sessão já existe");
        else{
            Optional<Pauta> pautaReferida = pautaService.getPautaById(sessaoVotacaoDTO.getIdPauta());
            if (pautaReferida.isEmpty()) {
                throw new IllegalArgumentException("Pauta não existe");
            }
            else{
                return sessaoVotacaoRepository.save(SessaoVotacaoMapper.dtoParaNovaSessaoVotacao(sessaoVotacaoDTO, pautaReferida.get()));
            }
        }
    }
    public List<SessaoVotacao> buscarSessoes() {
        return sessaoVotacaoRepository.findAll();
    }

    public List<SessaoVotacao> buscarSeesoesAbertas() {
        LocalDateTime dataAtual = LocalDateTime.now();
        return sessaoVotacaoRepository.findByDataFimGreaterThanAndDataInicioLessThanEqual(dataAtual, dataAtual);
    }
}
