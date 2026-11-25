/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba;

import br.com.ifba.usuario.view.UsuarioListagemView;
import javax.swing.SwingUtilities;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ProjetoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ProjetoApplication.class)
                .headless(false) // obrigatório em aplicação desktop
                .run(args);

        // a tela vem pronta do contêiner, com controller, service e dao já dentro
        UsuarioListagemView tela = context.getBean(UsuarioListagemView.class);
        SwingUtilities.invokeLater(() -> tela.setVisible(true));
    }
}
