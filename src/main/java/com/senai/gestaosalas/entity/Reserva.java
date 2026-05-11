package com.senai.gestaosalas.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_reserva")
    private Long id;

    private LocalDateTime data_reserva;

    private LocalDateTime data_pedido;

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "id_sala", nullable = false)
    private Sala sala;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    
    public Reserva(){
    }

    public Reserva(Long id, LocalDateTime data_reserva, LocalDateTime data_pedido, boolean status, Sala sala, Usuario usuario) {
        this.id = id;
        this.data_reserva = data_reserva;
        this.data_pedido = data_pedido;
        this.status = status;
        this.sala = sala;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData_reserva() {
        return data_reserva;
    }

    public void setData_reserva(LocalDateTime data_reserva) {
        this.data_reserva = data_reserva;
    }

    public LocalDateTime getData_devolucao() {
        return data_pedido;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
