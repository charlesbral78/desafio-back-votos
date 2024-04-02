package com.assembleia.votacao.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusPautaEnum {

    APROVADA,
    REJEITADA,
    EMPATE,
    NAO_VOTADA;
}
