/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.usuario.repository;

import br.com.ifba.usuario.dao.UsuarioIDao;
import br.com.ifba.usuario.entity.StatusUsuario;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.exception.UsuarioNaoEncontradoException;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioIDao {

    // Task 04: consulta derivada do nome. Só verifica se existe, não traz o usuário
    @Override
    boolean existsByLogin(String login);

    // consulta derivada: o Spring escreve o JPQL a partir do nome
    Optional<Usuario> findOptionalByLogin(String login);

    // o contrato do UsuarioIDao manda lançar a exceção quando não acha (regra da atividade 17)
    @Override
    default Usuario findByLogin(String login) {
        return findOptionalByLogin(login)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(login));
    }

    // o Spring Data não tem "update": o save() faz merge quando o id já existe
    @Override
    default Usuario update(Usuario usuario) {
        return ((JpaRepository<Usuario, Long>) this).save(usuario);
    }

    // mantém o comportamento de antes: ordenado por login, com a pessoa na mesma consulta
    @Override
    @Query("select u from Usuario u join fetch u.pessoa order by u.login")
    List<Usuario> findAll();

    @Override
    @Query("select u from Usuario u join fetch u.pessoa where u.status = :status order by u.login")
    List<Usuario> findByStatus(@Param("status") StatusUsuario status);

    // Task 05: duas condições e ordenação. O nome derivado ficaria ilegível
    @Override
    @Query("select u from Usuario u join fetch u.pessoa p "
            + "where lower(u.login) like lower(concat('%', :texto, '%')) "
            + "or lower(p.nome) like lower(concat('%', :texto, '%')) "
            + "order by u.login")
    List<Usuario> findByLoginOrNome(@Param("texto") String texto);
}