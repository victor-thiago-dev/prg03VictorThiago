/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.view;

import br.com.ifba.login.view.TelaCadastro;
import br.com.ifba.usuario.entity.StatusUsuario;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class UsuarioListagemView extends JFrame {

    private final DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"Login", "Nome", "Status"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable tabela = new JTable(modelo);
    private final TableRowSorter<DefaultTableModel> filtro = new TableRowSorter<>(modelo);
    private final JTextField txtBusca = new JTextField(20);
    private final JButton btnNovo = new JButton("+ Novo");
    private final JButton btnEditar = new JButton("Editar");
    private final JButton btnRemover = new JButton("Remover");

    public UsuarioListagemView() {
        super("Usuários");
        montarTela();
        carregarExemplos();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 400);
        setLocationRelativeTo(null);
    }

    private void montarTela() {
        // topo: busca + botão de novo cadastro
        JPanel topo = new JPanel(new BorderLayout(10, 0));
        topo.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        JPanel busca = new JPanel(new BorderLayout(5, 0));
        busca.add(new JLabel("Procurar:"), BorderLayout.WEST);
        busca.add(txtBusca, BorderLayout.CENTER);
        topo.add(busca, BorderLayout.CENTER);
        topo.add(btnNovo, BorderLayout.EAST);

        // centro: tabela
        tabela.setRowSorter(filtro);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // rodapé: editar / remover a linha selecionada
        JPanel rodape = new JPanel();
        rodape.add(btnEditar);
        rodape.add(btnRemover);

        add(topo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(rodape, BorderLayout.SOUTH);

        // busca: filtra a lista enquanto digita
        txtBusca.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filtrar(); }
            public void removeUpdate(DocumentEvent e) { filtrar(); }
            public void changedUpdate(DocumentEvent e) { filtrar(); }
        });

        btnNovo.addActionListener(e -> abrirCadastro());
        btnEditar.addActionListener(e -> editar());
        btnRemover.addActionListener(e -> remover());
    }

    private void filtrar() {
        String texto = txtBusca.getText().trim();
        filtro.setRowFilter(texto.isEmpty() ? null : RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(texto)));
    }

    // abre a TelaCadastro já existente
    private void abrirCadastro() {
        TelaCadastro tela = new TelaCadastro();
        // a TelaCadastro fecha o programa inteiro por padrão; aqui só fecha ela
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tela.setLocationRelativeTo(this);
        tela.setVisible(true);
    }

    private void editar() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário na lista.");
            return;
        }
        int m = tabela.convertRowIndexToModel(linha);
        String login = (String) modelo.getValueAt(m, 0);
        String nome = (String) modelo.getValueAt(m, 1);
        StatusUsuario status = StatusUsuario.valueOf((String) modelo.getValueAt(m, 2));
        new UsuarioEdicaoView(this, login, nome, status).setVisible(true);
    }

    private void remover() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário na lista.");
            return;
        }
        int m = tabela.convertRowIndexToModel(linha);
        int resposta = JOptionPane.showConfirmDialog(this,
                "Deseja excluir o usuário \"" + modelo.getValueAt(m, 0) + "\"?",
                "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "A exclusão será feita na Parte 2.");
        }
    }

    // linhas fixas só para visualizar a tela; saem na Parte 2, quando vier do banco
    private void carregarExemplos() {
        modelo.addRow(new Object[]{"joao.silva", "João Silva", "ATIVO"});
        modelo.addRow(new Object[]{"maria.souza", "Maria Souza", "ATIVO"});
        modelo.addRow(new Object[]{"carlos.lima", "Carlos Lima", "INATIVO"});
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UsuarioListagemView().setVisible(true));
    }
}