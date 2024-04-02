package com.assembleia.votacao.mapper;

import com.assembleia.votacao.models.SessaoVotacao;
import com.assembleia.votacao.models.Voto;
import com.assembleia.votacao.dtos.VotoDTO;
import com.assembleia.votacao.models.enums.VotoEnum;

public class VotoMapper {

    public static Voto dtoParaVoto(VotoDTO votoDTO, SessaoVotacao sessaoVotacao) {

        return Voto.builder()
                .idAssociado(votoDTO.getIdAssociado())
                .sessaoVotacao(sessaoVotacao)
                .voto(votoDTO.isVoto() ? VotoEnum.SIM : VotoEnum.NAO)
                .build();
    }

    public static VotoDTO votoParaDTO(Voto voto) {

        return VotoDTO.builder()
                .idAssociado(voto.getIdAssociado())
                .id_sessao_votacao(voto.getSessaoVotacao().getId())
                .voto(voto.getVoto().equals(VotoEnum.SIM)).build();
    }
}
