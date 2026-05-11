package com.senai.gestaosalas.controller;
import com.senai.gestaosalas.dto.Saladto;
import com.senai.gestaosalas.entity.Sala;
import com.senai.gestaosalas.service.SalaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value= "sala")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @PostMapping
    public ResponseEntity<Sala> criarSala(@RequestBody @Valid Saladto saladto) {
        Sala sala = new Sala();
        BeanUtils.copyProperties(saladto, sala);

        return ResponseEntity.status(HttpStatus.CREATED).body(salaService.criarSala(sala));
    }

    @GetMapping
        public List<Sala> findAll() {
        return salaService.findAll();
    }

    @GetMapping("/{id}")
    public Sala findById(@PathVariable Long id) {
        return salaService.findById(id);
    }

    @PutMapping( "/{id}")
    public ResponseEntity<Optional<Sala>> updateSala(@PathVariable Long id, @RequestBody @Valid Saladto saladto) {
        Sala sala = new Sala();
        BeanUtils.copyProperties(saladto, sala);

        Optional<Sala> mudanca = salaService.updateSala(id, sala);
        if (mudanca.isEmpty()) {
            ResponseEntity.status(HttpStatus.NO_CONTENT).body("Sala Inexistente");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(mudanca);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Optional<Sala>> deleteSala(@PathVariable Long id) {
        Optional<Sala> sala = salaService.deleteSala(id);

        if (sala.isEmpty()) {
            ResponseEntity.status(HttpStatus.NO_CONTENT).body("Sala Inexistente");
        }
        return ResponseEntity.status(HttpStatus.OK).body(sala);
    }
}