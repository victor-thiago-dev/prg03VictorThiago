/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.service;

import br.com.ifba.infrastructure.exception.NegocioException;
import br.com.ifba.pessoa.entity.Pessoa;
import br.com.ifba.usuario.dao.UsuarioIDao;
import br.com.ifba.usuario.entity.StatusUsuario;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.validar.ValidadorUsuario;
import java.util.List;
import java.util.Optional;

public class UsuarioService implements UsuarioIService {

    private final UsuarioIDao dao;

    public UsuarioService(UsuarioIDao dao) {
        this.dao = dao; // recebe, não cria
    }

    // regras de negócio: valem para qualquer tela (ou API) que chamar
    @Override
    public Usuario save(Usuario usuario) {
        if (usuario == null) {
            throw new NegocioException("Usuário não pode ser nulo.");
        }
        Pessoa pessoa = usuario.getPessoa();
        if (pessoa == null || !ValidadorUsuario.camposPreenchidos(
                pessoa.getNome(), pessoa.getCpf(), pessoa.getEmail(), usuario.getLogin())) {
            throw new NegocioException("Preencha nome, CPF, e-mail e login.");
        }
        if (!ValidadorUsuario.cpfValido(pessoa.getCpf())) {
            throw new NegocioException("CPF inválido.");
        }
        if (ValidadorUsuario.contemPalavraProibida(usuario.getLogin())) {
            throw new NegocioException("Login contém palavra não permitida.");
        }
        if (!ValidadorUsuario.senhaForte(usuario.getSenha())) {
            throw new NegocioException("A senha deve ter no mínimo "
                    + ValidadorUsuario.TAMANHO_MINIMO_SENHA + " caracteres.");
        }
        if (dao.existsByLogin(usuario.getLogin())) {
            throw new NegocioException("Já existe um usuário com esse login.");
        }
        return dao.save(usuario);
    }

    @Override
    public Usuario update(Usuario usuario) {
        if (usuario == null || usuario.getPessoa() == null
                || !ValidadorUsuario.camposPreenchidos(usuario.getPessoa().getNome())) {
            throw new NegocioException("O nome é obrigatório.");
        }
        if (usuario.getStatus() == null) {
            throw new NegocioException("O status é obrigatório.");
        }
        return dao.update(usuario);
    }

    @Override
    public void delete(Usuario usuario) {
        dao.delete(usuario);
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Usuario> findAll() {
        return dao.findAll();
    }

    @Override
    public Usuario findByLogin(String login) {
        return dao.findByLogin(login);
    }

    @Override
    public List<Usuario> findByStatus(StatusUsuario status) {
        return dao.findByStatus(status);
    }

    // busca em branco = lista tudo
    @Override
    public List<Usuario> findByLoginOrNome(String texto) {
        if (texto == null || texto.isBlank()) {
            return dao.findAll();
        }
        return dao.findByLoginOrNome(texto.trim());
    }
}
