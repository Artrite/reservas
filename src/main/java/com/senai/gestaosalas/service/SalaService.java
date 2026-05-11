package com.senai.gestaosalas.service;

import com.senai.gestaosalas.entity.Sala;
import com.senai.gestaosalas.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    public Sala criarSala(Sala sala) {
        return salaRepository.save(sala);
    }

    public List<Sala> findAll() {
        return salaRepository.findAll();
    }

    public Sala findById(Long id) {
        Sala sala = salaRepository.findById(id).orElse(null);
        return sala;
    }

   public Optional<Sala> updateSala (Long id, Sala newsala) {
        Optional<Sala> sala = salaRepository.findById(id);
        if (sala.isEmpty()) {
            return sala;
        }
        return Optional.of(salaRepository.save(newsala));
   }

    public Optional<Sala> deleteSala(Long id) {
        Optional<Sala> sala = salaRepository.findById(id);

        if(sala.isPresent()) {
            salaRepository.deleteById(id);
        }
        return sala;
    }
}