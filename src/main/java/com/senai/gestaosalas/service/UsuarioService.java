package com.senai.gestaosalas.service;

import com.senai.gestaosalas.entity.Usuario;
import com.senai.gestaosalas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> findAll() {
            return repository.findAll();
    }

    public Usuario salvarUsuario(Usuario usuario){
        return repository.save(usuario);
    }

    public Usuario findbyID(Long id) {
        Usuario usuario = repository.findById(id).orElse(null);
        return usuario;
    }

    public Optional<Usuario> atualizarUsuario(Long id, Usuario newusuario) {
        Optional<Usuario> id1 = repository.findById(id);

        if(id1.isEmpty()){
        return id1;
        }
        return Optional.of(repository.save(newusuario));
    }

    public Optional<Usuario> deleteUsuario(Long id) {

        Optional<Usuario> usuario = repository.findById(id);

        if(usuario.isPresent()) {
            repository.delete(usuario.get());
        }
        return usuario;
        }
    }

