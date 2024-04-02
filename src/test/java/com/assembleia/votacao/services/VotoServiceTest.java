package com.assembleia.votacao.services;

import com.assembleia.votacao.dtos.VotoDTO;
import com.assembleia.votacao.repositories.SessaoVotacaoRepository;
import com.assembleia.votacao.repositories.VotoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class VotoServiceTest {

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @InjectMocks
    private VotoService votoService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSalvarVoto_SessaoNaoExiste_ThrowsIllegalArgumentException() {
        VotoDTO votoDTO = new VotoDTO();
        when(sessaoVotacaoRepository.findById(votoDTO.getId_sessao_votacao())).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> votoService.salvarVoto(votoDTO));

        verify(votoRepository, times(0)).save(any());
    }

}