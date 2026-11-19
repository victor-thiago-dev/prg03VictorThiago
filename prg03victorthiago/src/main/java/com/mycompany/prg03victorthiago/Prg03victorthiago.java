/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.prg03victorthiago;

import br.com.ifba.pessoa.entity.Pessoa;
import br.com.ifba.infrastructure.util.JPAUtil;
import br.com.ifba.infrastructure.util.MigrationRunner;
import br.com.ifba.usuario.controller.UsuarioController;
import br.com.ifba.usuario.controller.UsuarioIController;
import br.com.ifba.usuario.dao.UsuarioDao;
import br.com.ifba.usuario.dao.UsuarioIDao;
import br.com.ifba.usuario.service.UsuarioIService;
import br.com.ifba.usuario.service.UsuarioService;
import br.com.ifba.usuario.view.UsuarioListagemView;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author victo
 */
public class Prg03victorthiago {
    public static String apresentarPessoa(Pessoa pessoa){
        return pessoa.descreverFuncao();
    }
    
    public static void main(String[] args) { 
         // 1º: banco atualizado (Flyway)
         try {
            MigrationRunner.migrar();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null,
                    "Falha ao atualizar o banco: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        Runtime.getRuntime().addShutdownHook(new Thread(JPAUtil::fechar));

        // 2º: monta a pilha, de baixo para cima: DAO -> Service -> Controller -> tela
        UsuarioIDao dao = new UsuarioDao();
        UsuarioIService service = new UsuarioService(dao);
        UsuarioIController controller = new UsuarioController(service);

        SwingUtilities.invokeLater(() -> new UsuarioListagemView(controller).setVisible(true));
    }
        
    
}
