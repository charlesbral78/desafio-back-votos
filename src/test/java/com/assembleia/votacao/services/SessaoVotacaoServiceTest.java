package com.assembleia.votacao.services;

import com.assembleia.votacao.dtos.SessaoVotacaoDTO;
import com.assembleia.votacao.mapper.SessaoVotacaoMapper;
import com.assembleia.votacao.models.Pauta;
import com.assembleia.votacao.models.SessaoVotacao;
import com.assembleia.votacao.repositories.SessaoVotacaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SessaoVotacaoServiceTest {

    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Mock
    private PautaService pautaService;

    @Mock
    private SessaoVotacaoMapper sessaoVotacaoMapper;

    @InjectMocks
    private SessaoVotacaoService sessaoVotacaoService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSalvarSessaoVotacao_SessaoJaExiste_ThrowsIllegalArgumentException() {
        SessaoVotacaoDTO sessaoVotacaoDTO = new SessaoVotacaoDTO();
        when(sessaoVotacaoRepository.findByPautaId(sessaoVotacaoDTO.getIdPauta())).thenReturn(Optional.of(new SessaoVotacao()));

        Assertions.assertThrows(IllegalArgumentException.class, () -> sessaoVotacaoService.salvarSessaoVotacao(sessaoVotacaoDTO));

        verify(sessaoVotacaoRepository, times(0)).save(any());
    }

    @Test
    public void testSalvarSessaoVotacao_PautaNaoExiste_ThrowsIllegalArgumentException() {
        SessaoVotacaoDTO sessaoVotacaoDTO = new SessaoVotacaoDTO();
        when(sessaoVotacaoRepository.findByPautaId(sessaoVotacaoDTO.getIdPauta())).thenReturn(Optional.empty());
        when(pautaService.getPautaById(sessaoVotacaoDTO.getIdPauta())).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> sessaoVotacaoService.salvarSessaoVotacao(sessaoVotacaoDTO));

        verify(sessaoVotacaoRepository, times(0)).save(any());
    }

    @Test
    public void testBuscarSessoes_ReturnsAllSessoes() {
        List<SessaoVotacao> sessoes = new ArrayList<>();
        when(sessaoVotacaoRepository.findAll()).thenReturn(sessoes);

        List<SessaoVotacao> result = sessaoVotacaoService.buscarSessoes();

        assertEquals(sessoes, result);
        verify(sessaoVotacaoRepository, times(1)).findAll();
    }

}