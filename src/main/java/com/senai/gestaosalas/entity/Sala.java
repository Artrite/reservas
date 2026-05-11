package com.senai.gestaosalas.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Sala implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sala")
    private Long id;

    private String nome;

    private String departamento;

    private String descricao;

    private int status;

    public Sala(Long id, String nome, String departamento, String descricao, int status) {
        this.id = id;
        this.nome = nome;
        this.departamento = departamento;
        this.descricao = descricao;
        this.status = status;
    }

    public Sala() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
