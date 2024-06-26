package com.assembleia.votacao.mapper;

import com.assembleia.votacao.models.Pauta;
import com.assembleia.votacao.models.SessaoVotacao;
import com.assembleia.votacao.dtos.SessaoVotacaoDTO;

import java.time.Duration;
import java.time.LocalDateTime;

public class SessaoVotacaoMapper {

    public static SessaoVotacao dtoParaNovaSessaoVotacao(SessaoVotacaoDTO sessaoVotacaoDTO, Pauta pauta) {

        LocalDateTime dataInicio = LocalDateTime.now();
        LocalDateTime dataFim;
        if (sessaoVotacaoDTO.getTempoAberturaSessao() == null)
            dataFim = dataInicio.plusMinutes(sessaoVotacaoDTO.getTEMPO_DEFAULT());
        else
            dataFim = dataInicio.plusMinutes(sessaoVotacaoDTO.getTempoAberturaSessao());

        return SessaoVotacao.builder()
                .dataInicio(dataInicio)
                .dataFim(dataFim)
                .pauta(pauta)
                .build();
    }

    public static SessaoVotacaoDTO sessaoVotacaoParaNovaDTO(SessaoVotacao sessaoVotacao) {

        Integer diferencaEmMinutos = (int) Duration.between(sessaoVotacao.getDataInicio(), sessaoVotacao.getDataFim()).toMinutes();

        return SessaoVotacaoDTO.builder()
                .idPauta(sessaoVotacao.getPauta().getId())
                .tempoAberturaSessao(diferencaEmMinutos)
                .build();

    }
}
