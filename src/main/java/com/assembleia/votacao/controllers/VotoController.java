package com.assembleia.votacao.controllers;

import javax.validation.Valid;

import com.assembleia.votacao.dtos.VotoDTO;
import com.assembleia.votacao.services.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assembleia.votacao.models.Voto;

@RestController
@RequestMapping("/voto")
public class VotoController {
    @Autowired
    VotoService votoService;

    @PostMapping
    public ResponseEntity<Voto> salvarVoto(@Valid @RequestBody VotoDTO votoDTO) {
        return new ResponseEntity<>(votoService.salvarVoto(votoDTO), HttpStatus.CREATED);
    }

}
