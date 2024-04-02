package com.assembleia.votacao.mapper;

import com.assembleia.votacao.models.Pauta;
import com.assembleia.votacao.dtos.PautaDTO;

public class PautaMapper {

    public static Pauta dtoParaPauta(PautaDTO pautaDTO){
        return Pauta.builder()
                .descricao(pautaDTO.getDescricao())
                .build();
    }

    public static PautaDTO pautaParaDTO(Pauta pauta){
        return PautaDTO.builder()
                .descricao(pauta.getDescricao())
                .build();
    }
}
