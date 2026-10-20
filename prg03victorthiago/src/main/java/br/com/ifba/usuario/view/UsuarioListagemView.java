/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.view;

import br.com.ifba.infrastructure.util.JPAUtil;
import br.com.ifba.usuario.entity.StatusUsuario;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.exception.UsuarioNaoEncontradoException;
import br.com.ifba.usuario.repositorio.UsuarioRepositorioJpa;
import jakarta.persistence.PersistenceException;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class UsuarioListagemView extends JFrame {

    private final UsuarioRepositorioJpa repositorio = new UsuarioRepositorioJpa();

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

    public UsuarioListagemView() {
        super("Usuários");
        montarTela();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 400);
        setLocationRelativeTo(null);

        // fecha a fábrica do JPA ao encerrar a aplicação
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JPAUtil.fechar();
            }
        });

        carregar();
    }

    private void montarTela() {
        // topo: busca + botão de novo cadastro
        JPanel topo = new JPanel(new BorderLayout(10, 0));
        topo.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        JPanel busca = new JPanel(new BorderLayout(5, 0));
        busca.add(new JLabel("Procurar:"), BorderLayout.WEST);
        busca.add(txtBusca, BorderLayout.CENTER);
        busca.add(btnBuscar, BorderLayout.EAST);
        topo.add(busca, BorderLayout.CENTER);
        topo.add(btnNovo, BorderLayout.EAST);

        // centro: tabela
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // rodapé: editar / remover a linha selecionada
        JPanel rodape = new JPanel();
        rodape.add(btnEditar);
        rodape.add(btnRemover);

        add(topo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(rodape, BorderLayout.SOUTH);

        btnBuscar.addActionListener(e -> carregar());
        txtBusca.addActionListener(e -> carregar()); // Enter no campo
        btnNovo.addActionListener(e -> abrirCadastro());
        btnEditar.addActionListener(e -> editar());
        btnRemover.addActionListener(e -> remover());
    }

    // lista tudo, ou busca por JPQL se houver texto no campo
    private void carregar() {
        String texto = txtBusca.getText().trim();
        modelo.setRowCount(0);
        try {
            List<Usuario> usuarios = texto.isEmpty()
                    ? repositorio.listarTodos()
                    : repositorio.buscarPorTexto(texto);
            for (Usuario u : usuarios) {
                modelo.addRow(new Object[]{u.getLogin(), u.getPessoa().getNome(), u.getStatus().name()});
            }
        } catch (PersistenceException e) {
            JOptionPane.showMessageDialog(this,
                    "Não foi possível carregar os usuários. Verifique se o banco de dados está acessível.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirCadastro() {
        new UsuarioCadastroView(this).setVisible(true); // modal: continua aqui ao fechar
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
        new UsuarioEdicaoView(this, login, nome, status).setVisible(true);
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
            repositorio.remover(login);
            JOptionPane.showMessageDialog(this, "Usuário removido com sucesso.");
        } catch (UsuarioNaoEncontradoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
        } catch (PersistenceException e) {
            JOptionPane.showMessageDialog(this,
                    "Não foi possível remover. Verifique se o banco de dados está acessível.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
        carregar();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UsuarioListagemView().setVisible(true));
    }
}