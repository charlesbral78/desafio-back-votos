package com.assembleia.votacao.controllers;

import javax.validation.Valid;

import com.assembleia.votacao.dtos.PautaDTO;
import com.assembleia.votacao.dtos.ResultadoPautaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.assembleia.votacao.models.Pauta;
import com.assembleia.votacao.services.PautaService;

import java.util.List;

@RestController
@RequestMapping("/pauta")
public class PautaController {
    @Autowired
    PautaService pautaService;

    @PostMapping
    public ResponseEntity<Pauta> salvarPauta(@Valid @RequestBody PautaDTO pautaDTO) {
        return new ResponseEntity<>(pautaService.salvarPauta(pautaDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pauta>> listarPautas(){
        List<Pauta> pautas = this.pautaService.buscarPautas();
        return pautas.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(pautas, HttpStatus.OK);
    }

    @GetMapping("/resultado/{id}")
    public ResponseEntity<ResultadoPautaDTO> resultadoVotacao(@PathVariable Long id) {
        ResultadoPautaDTO resultadoPauta = this.pautaService.resultadoPauta(id);
        return new ResponseEntity<>(resultadoPauta, HttpStatus.OK);
    }
}