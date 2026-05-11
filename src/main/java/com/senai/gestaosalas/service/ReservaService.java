package com.senai.gestaosalas.service;

import com.senai.gestaosalas.Exception.ExpectionReserva;
import com.senai.gestaosalas.entity.Reserva;
import com.senai.gestaosalas.entity.Sala;
import com.senai.gestaosalas.entity.Usuario;
import com.senai.gestaosalas.repository.ReservaRepository;
import com.senai.gestaosalas.repository.SalaRepository;
import com.senai.gestaosalas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service

public class ReservaService {

        @Autowired
        ReservaRepository reservaRepository;

        @Autowired
        UsuarioRepository usuarioRepository;

        @Autowired
        SalaRepository salaRepository;

        public List<Reserva> findAll(){
            return reservaRepository.findAll();
        }

        public Optional<Reserva> findById(Long id){
            return reservaRepository.findById(id);
        }

        public List<Reserva> findUsuario (Long id_usuario) {
            return reservaRepository.findByidUsuario(id_usuario);
        }

        public List<Reserva> findSala (Long id_sala) {
            return reservaRepository.findByidSala(id_sala);
        }

        public Optional<Reserva> deleteById(Long id){
            Optional<Reserva> id1 = reservaRepository.findById(id);

            if(id1.isPresent()){
                reservaRepository.deleteById(id);
            }
            return id1;
        }
        public Optional<Reserva> atualizarReserva (Long id, Reserva newreserva){
            Optional<Reserva> id1 = reservaRepository.findById(newreserva.getId());

            if(id1.isEmpty()){
                return id1;
            }
            return Optional.of(reservaRepository.save(newreserva));
        }

        public Reserva createReserva (Reserva reserva, Long idUsuario, Long idSala) throws ExpectionReserva {

            Optional<Usuario> id1 = usuarioRepository.findById(idUsuario);
            if (id1.isEmpty()) {
                throw new ExpectionReserva("Usuario inexistente");
            }
            reserva.setUsuario(id1.get());

            Optional<Sala> id2 = salaRepository.findById(idSala);
            if (id2.isEmpty()) {
                throw new ExpectionReserva("Sala inexistente");
            }
            reserva.setSala(id2.get());

            LocalDateTime localDateTime = LocalDateTime.now();
            if (reserva.getData_devolucao().isBefore(localDateTime)) {
                throw new ExpectionReserva("Data pedido invalido");
            }

            long diasDeDiferenca = ChronoUnit.DAYS.between(reserva.getData_reserva(), reserva.getData_devolucao());
            if (diasDeDiferenca == 30) {
                throw new ExpectionReserva("Seu pedido passou dos 30 dias de diferença");
            }

            List<Integer> dataativa = reservaRepository.findByData_pedidoativa(reserva.getData_devolucao());
            if (!dataativa.isEmpty()) {
                throw new ExpectionReserva("Alguem ja reservou essa sala");
            }

            List<Integer> usuarioativo = reservaRepository.findByIdUsuarioAtivo(reserva.getUsuario().getId());
            if (!usuarioativo.isEmpty()) {
                throw new ExpectionReserva("Usuario ja possui reserva marcada");
            }
            return reservaRepository.save(reserva);
        }
}
