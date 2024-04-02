package com.assembleia.votacao.models;

import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "pauta")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pauta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String descricao;

    public Pauta(Long idPauta) {
        this.id = idPauta;
    }

}