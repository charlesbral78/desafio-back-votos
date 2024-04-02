package com.assembleia.votacao.services.utils;

import com.assembleia.votacao.models.SessaoVotacao;
import com.assembleia.votacao.models.Voto;
import com.assembleia.votacao.dtos.ResultadoPautaDTO;
import com.assembleia.votacao.dtos.VotoResponseDTO;
import com.assembleia.votacao.models.enums.StatusPautaEnum;
import com.assembleia.votacao.models.enums.StatusSessaoVotacaoEnum;
import com.assembleia.votacao.models.enums.VotoEnum;
import com.assembleia.votacao.repositories.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ResultadoPautaDTOUtils {

    @Autowired
    VotoRepository votoRepository;

    public ResultadoPautaDTO getResultadoPautaDTOFromSessaoVotacao(SessaoVotacao sessaoVotacao) {
        ResultadoPautaDTO resultadoPauta = new ResultadoPautaDTO();
        List<VotoResponseDTO> listaVotos = converteParaVotoResponseDTO(votoRepository.findAllBySessaoVotacaoId(sessaoVotacao.getId()));
        resultadoPauta.setListaVotos(listaVotos);
        resultadoPauta.setStatusSessao(calculaStatusSessaoVotacao(sessaoVotacao));
        resultadoPauta.setStatusPauta(calculaStatusPauta(listaVotos));
        return resultadoPauta;
    }
    public List<VotoResponseDTO> converteParaVotoResponseDTO(List<Voto> votos) {
        List<VotoResponseDTO> novaListaVotos = new ArrayList<>();
        votos.forEach(voto -> {
            novaListaVotos.add(new VotoResponseDTO(voto.getIdAssociado(), voto.getVoto()));
        }
      );
        return novaListaVotos;
    }
    public StatusSessaoVotacaoEnum calculaStatusSessaoVotacao(SessaoVotacao sessaoVotacao) {
        LocalDateTime dataAtual= LocalDateTime.now();
        if (dataAtual.isAfter(sessaoVotacao.getDataFim()) || dataAtual.isBefore(sessaoVotacao.getDataInicio()))
            return StatusSessaoVotacaoEnum.FECHADA;
        else
            return StatusSessaoVotacaoEnum.ABERTA;
    }

    public StatusPautaEnum calculaStatusPauta(List<VotoResponseDTO> listaVotos) {
        int resultado = 0;
        for (VotoResponseDTO voto : listaVotos)
            resultado += voto.getVoto().equals(VotoEnum.SIM) ? +1 : -1;

        if (listaVotos.isEmpty())
            return (StatusPautaEnum.NAO_VOTADA);
        else if (resultado == 0)
            return (StatusPautaEnum.EMPATE);
        else if (resultado > 0)
            return (StatusPautaEnum.APROVADA);
        else
            return (StatusPautaEnum.REJEITADA);
    }
}
