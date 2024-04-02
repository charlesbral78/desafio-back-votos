package com.assembleia.votacao.services;

import com.assembleia.votacao.mapper.PautaMapper;
import com.assembleia.votacao.models.SessaoVotacao;
import com.assembleia.votacao.dtos.PautaDTO;
import com.assembleia.votacao.dtos.ResultadoPautaDTO;
import com.assembleia.votacao.repositories.SessaoVotacaoRepository;
import com.assembleia.votacao.services.utils.ResultadoPautaDTOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assembleia.votacao.models.Pauta;
import com.assembleia.votacao.repositories.PautaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PautaService {

    @Autowired
    PautaRepository pautaRepository;

    @Autowired
    SessaoVotacaoRepository sessaoVotacaoRepository;

    @Autowired
    ResultadoPautaDTOUtils resultadoPautaDTOUtils;

    public Pauta salvarPauta(PautaDTO pautaDTO) {
        Pauta pauta = PautaMapper.dtoParaPauta(pautaDTO);
        return pautaRepository.save(pauta);

    }
    public List<Pauta> buscarPautas() {
        return pautaRepository.findAll();
    }
    public Optional<Pauta> getPautaById(Long idPauta) {
        return pautaRepository.findById(idPauta);
    }

    public ResultadoPautaDTO resultadoPauta(Long idPauta){

        Optional<Pauta> pauta = pautaRepository.findById(idPauta);

        if (pauta.isEmpty())
            throw new IllegalArgumentException("Pauta não existe!");
        else {
            Optional<SessaoVotacao> sessaoVotacao = sessaoVotacaoRepository.findByPautaId(idPauta);
            if (sessaoVotacao.isEmpty())
                throw new IllegalArgumentException("Sessão de votação não existe!");
            else{
                return resultadoPautaDTOUtils.getResultadoPautaDTOFromSessaoVotacao(sessaoVotacao.get());
            }
        }

    }
}
