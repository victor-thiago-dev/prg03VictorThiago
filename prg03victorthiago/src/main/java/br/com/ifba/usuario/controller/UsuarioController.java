/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.controller;

import br.com.ifba.usuario.entity.StatusUsuario;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.service.UsuarioIService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;

@Controller
public class UsuarioController implements UsuarioIController {

    private final UsuarioIService service;

    public UsuarioController(UsuarioIService service) {
        this.service = service; // recebe, não cria
    }

    @Override
    public Usuario save(Usuario usuario) {
        return service.save(usuario);
    }

    @Override
    public Usuario update(Usuario usuario) {
        return service.update(usuario);
    }

    @Override
    public void delete(Usuario usuario) {
        service.delete(usuario);
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return service.findById(id);
    }

    @Override
    public List<Usuario> findAll() {
        return service.findAll();
    }

    @Override
    public Usuario findByLogin(String login) {
        return service.findByLogin(login);
    }

    @Override
    public List<Usuario> findByStatus(StatusUsuario status) {
        return service.findByStatus(status);
    }

    @Override
    public List<Usuario> findByLoginOrNome(String texto) {
        return service.findByLoginOrNome(texto);
    }
}
