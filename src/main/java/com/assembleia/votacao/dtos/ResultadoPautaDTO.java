package com.assembleia.votacao.dtos;

import com.assembleia.votacao.models.enums.StatusPautaEnum;
import com.assembleia.votacao.models.enums.StatusSessaoVotacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoPautaDTO {

    private List<VotoResponseDTO> listaVotos;

    private StatusSessaoVotacaoEnum statusSessao;

    private StatusPautaEnum statusPauta;

}
