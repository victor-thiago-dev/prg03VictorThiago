/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.view;

import br.com.ifba.infrastructure.exception.PersistenciaException;
import br.com.ifba.usuario.controller.UsuarioIController;
import br.com.ifba.usuario.entity.StatusUsuario;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.exception.UsuarioNaoEncontradoException;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class UsuarioListagemView extends JFrame {

    private final UsuarioIController controller;

    private final DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"Login", "Nome", "Status"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable tabela = new JTable(modelo);
    private final JTextField txtBusca = new JTextField(20);
    private final JButton btnBuscar = new JButton("Buscar");
    private final JButton btnNovo = new JButton("+ Novo");
    private final JButton btnEditar = new JButton("Editar");
    private final JButton btnRemover = new JButton("Remover");

    public UsuarioListagemView(UsuarioIController controller) {
        super("Usuários");
        this.controller = controller; // recebe, não cria
        montarTela();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 400);
        setLocationRelativeTo(null);
        carregar();
    }

    private void montarTela() {
        JPanel topo = new JPanel(new BorderLayout(10, 0));
        topo.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        JPanel busca = new JPanel(new BorderLayout(5, 0));
        busca.add(new JLabel("Procurar:"), BorderLayout.WEST);
        busca.add(txtBusca, BorderLayout.CENTER);
        busca.add(btnBuscar, BorderLayout.EAST);
        topo.add(busca, BorderLayout.CENTER);
        topo.add(btnNovo, BorderLayout.EAST);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JPanel rodape = new JPanel();
        rodape.add(btnEditar);
        rodape.add(btnRemover);

        add(topo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(rodape, BorderLayout.SOUTH);

        btnBuscar.addActionListener(e -> carregar());
        txtBusca.addActionListener(e -> carregar());
        btnNovo.addActionListener(e -> abrirCadastro());
        btnEditar.addActionListener(e -> editar());
        btnRemover.addActionListener(e -> remover());
    }

    // busca em branco = lista tudo (essa regra é do Service)
    private void carregar() {
        modelo.setRowCount(0);
        try {
            List<Usuario> usuarios = controller.findByLoginOrNome(txtBusca.getText());
            for (Usuario u : usuarios) {
                modelo.addRow(new Object[]{u.getLogin(), u.getPessoa().getNome(), u.getStatus().name()});
            }
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirCadastro() {
        new UsuarioCadastroView(this, controller).setVisible(true);
        carregar();
    }

    private void editar() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário na lista.");
            return;
        }
        String login = (String) modelo.getValueAt(linha, 0);
        String nome = (String) modelo.getValueAt(linha, 1);
        StatusUsuario status = StatusUsuario.valueOf((String) modelo.getValueAt(linha, 2));
        new UsuarioEdicaoView(this, controller, login, nome, status).setVisible(true);
        carregar();
    }

    private void remover() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário na lista.");
            return;
        }
        String login = (String) modelo.getValueAt(linha, 0);
        int resposta = JOptionPane.showConfirmDialog(this,
                "Deseja excluir o usuário \"" + login + "\"?",
                "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
        if (resposta != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            controller.delete(controller.findByLogin(login));
            JOptionPane.showMessageDialog(this, "Usuário removido com sucesso.");
        } catch (UsuarioNaoEncontradoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        carregar();
    }
}