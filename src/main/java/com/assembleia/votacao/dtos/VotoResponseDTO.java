package com.assembleia.votacao.dtos;

import com.assembleia.votacao.models.enums.VotoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VotoResponseDTO {

    private Long idAssociado;

    private VotoEnum voto;
}
