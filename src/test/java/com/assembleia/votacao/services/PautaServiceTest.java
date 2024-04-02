package com.assembleia.votacao.services;

import com.assembleia.votacao.dtos.PautaDTO;
import com.assembleia.votacao.dtos.ResultadoPautaDTO;
import com.assembleia.votacao.mapper.PautaMapper;
import com.assembleia.votacao.models.Pauta;
import com.assembleia.votacao.models.SessaoVotacao;
import com.assembleia.votacao.repositories.PautaRepository;
import com.assembleia.votacao.repositories.SessaoVotacaoRepository;
import com.assembleia.votacao.services.utils.ResultadoPautaDTOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PautaServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Mock
    private ResultadoPautaDTOUtils resultadoPautaDTOUtils;

    @InjectMocks
    private PautaService pautaService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSalvarPauta() {
        PautaDTO pautaDTO = new PautaDTO();
        Pauta pauta = PautaMapper.dtoParaPauta(pautaDTO);
        when(pautaRepository.save(pauta)).thenReturn(pauta);

        Pauta savedPauta = pautaService.salvarPauta(pautaDTO);

        assertEquals(pauta, savedPauta);
    }

    @Test
    public void testBuscarPautas() {
        Pauta pauta1 = new Pauta();
        Pauta pauta2 = new Pauta();
        List<Pauta> pautas = Arrays.asList(pauta1, pauta2);
        when(pautaRepository.findAll()).thenReturn(pautas);

        List<Pauta> result = pautaService.buscarPautas();

        assertEquals(pautas, result);
    }

    @Test
    public void testGetPautaById() {
        Pauta pauta = new Pauta();
        when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));

        Optional<Pauta> result = pautaService.getPautaById(1L);

        assertEquals(Optional.of(pauta), result);
    }

    @Test
    public void testResultadoPauta_PautaNotFound() {
        when(pautaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> pautaService.resultadoPauta(1L));
    }

    @Test
    public void testResultadoPauta_SessaoVotacaoNotFound() {
        Pauta pauta = new Pauta();
        when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));
        when(sessaoVotacaoRepository.findByPautaId(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> pautaService.resultadoPauta(1L));
    }

}