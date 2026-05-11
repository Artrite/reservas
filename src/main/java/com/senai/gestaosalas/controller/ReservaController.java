package com.senai.gestaosalas.controller;

import com.senai.gestaosalas.Exception.ExpectionReserva;
import com.senai.gestaosalas.dto.Reservadto;
import com.senai.gestaosalas.entity.Reserva;
import com.senai.gestaosalas.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Reserva> findAll() {
        return reservaService.findAll();
    }

    @GetMapping("/{id}")
        public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<Reserva> reserva = reservaService.findById(id);
    
        if (reserva.isEmpty()) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva inexistente");
         }
            return ResponseEntity.status(HttpStatus.OK).body(reserva.get());
    }

    @GetMapping("/{id_usuario}/usuario")
    private ResponseEntity<Object> reservaUsuario(@PathVariable Long id_usuario){

        List<Reserva> id1 = reservaService.findUsuario(id_usuario);

        if(id1.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não possui reservas");
        }
        return ResponseEntity.status(HttpStatus.OK).body(id1);
    }

    @GetMapping("/{id_sala}/sala")
    private ResponseEntity<Object> reservaSala(@PathVariable Long id_sala){
        List<Reserva> id1 = reservaService.findSala(id_sala);

        if(id1.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sala inexistente");
        }
        return ResponseEntity.status(HttpStatus.OK).body(id1);
    }

    @PostMapping
    public ResponseEntity<String> criarReserva(@RequestBody @Valid Reservadto reservadto) throws ExpectionReserva {

        try {

        Reserva reserva  = new Reserva();
        BeanUtils.copyProperties(reservadto, reserva);
        reserva.setStatus(reservadto.status() == 1);

            reserva = reservaService.createReserva(reserva, reservadto.id_sala(), reservadto.id_usuario());

            return ResponseEntity.status(HttpStatus.CREATED).body(reserva.toString());

        } catch (ExpectionReserva e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Object atualizarReserva(@PathVariable Long id, @RequestBody @Valid Reservadto reservadto) {

        Reserva reserva = new Reserva();
        BeanUtils.copyProperties(reservadto, reserva);

        reserva.setStatus(reservadto.status() == 1);
        Optional<Reserva> mudanca = reservaService.atualizarReserva(id,reserva);

        if(mudanca.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva inexistente");
        }

        return ResponseEntity.status(HttpStatus.OK).body(mudanca);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletebyID(@PathVariable long id) {
        Optional<Reserva> reserva = reservaService.deleteById(id);

        if (reserva.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Reserva Inexistente");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Reserva deletada com sucesso");
    }

    }

