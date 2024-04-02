package com.assembleia.votacao.models;

import com.assembleia.votacao.models.enums.VotoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "voto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Voto implements Serializable {


    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @NotNull(message = "O campo id_associado é obrigatorio")
    private Long idAssociado;

    @ManyToOne
    @JoinColumn(name = "fk_sessao_votacao")
    @NotNull(message = "O campo id_sessao_votacao é obrigatorio")
    private SessaoVotacao sessaoVotacao;

    @NotNull(message = "O campo voto é obrigatorio")
    private VotoEnum voto;
}
