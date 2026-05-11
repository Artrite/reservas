package com.senai.gestaosalas.controller;
import com.senai.gestaosalas.dto.Usuariodto;
import com.senai.gestaosalas.entity.Usuario;
import com.senai.gestaosalas.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (value = "usuario")
public class UsuarioController {

   @Autowired
   private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getUsuarios() {
       return usuarioService.findAll();
    }

    @PostMapping
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody @Valid Usuariodto usuariodto) {

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuariodto, usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(usuario));
    }

    @GetMapping( "/{id}")
    public Usuario findByID(@PathVariable Long id) {
        return usuarioService.findbyID(id);
    }

    @PutMapping("/{id}")
    public Object atualizarUsuario(@PathVariable Long id, @RequestBody @Valid Usuariodto usuariodto) {

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuariodto, usuario);

        Optional<Usuario> mudanca = usuarioService.atualizarUsuario(id, usuario);

        if(mudanca.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario inexistente");
        }

        return ResponseEntity.status(HttpStatus.OK).body(mudanca);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletebyID(@PathVariable long id) {
        Optional<Usuario> usuario = usuarioService.deleteUsuario(id);

        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inexistente");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso");
    }
}
