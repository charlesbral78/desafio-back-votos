package com.assembleia.votacao.dtos;

import javax.validation.constraints.NotNull;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessaoVotacaoDTO {

    private final int TEMPO_DEFAULT = 1;

    @NotNull(message = "O campo id_pauta é obrigatorio")
    private Long idPauta;

    private Integer tempoAberturaSessao;

}
