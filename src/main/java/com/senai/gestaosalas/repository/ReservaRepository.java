package com.senai.gestaosalas.repository;

import com.senai.gestaosalas.entity.Reserva;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDateTime;
import java.util.List;

@Transactional
public interface ReservaRepository  extends JpaRepository<Reserva, Long> {

    @Query("select r from Reserva r where r.usuario.id_usuario = :id")
    List<Reserva> findByidUsuario(Long id);

    @Query("select r.id from Reserva r where r.usuario.id = :id and r.status = true ")
    List<Integer> findByIdUsuarioAtivo(Long id);

    @Query("select r from Reserva r where r.sala.id = :id")
    List<Reserva> findByidSala(Long id);

    @Query("select r.id from Reserva r where r.data_pedido = :data and r.status = true ")
    List<Integer> findByData_pedidoativa(LocalDateTime data);
}