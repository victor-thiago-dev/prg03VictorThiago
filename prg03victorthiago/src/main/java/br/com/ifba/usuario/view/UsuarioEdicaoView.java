/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.view;

import br.com.ifba.usuario.entity.StatusUsuario;
import br.com.ifba.usuario.exception.UsuarioNaoEncontradoException;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import br.com.ifba.infrastructure.exception.PersistenciaException;
import br.com.ifba.usuario.dao.UsuarioDao;
import br.com.ifba.usuario.dao.UsuarioIDao;
import br.com.ifba.usuario.entity.Usuario;

public class UsuarioEdicaoView extends JDialog {

    private final UsuarioIDao usuarioDao = new UsuarioDao();

    private final JTextField txtLogin = new JTextField(20);
    private final JTextField txtNome = new JTextField(20);
    private final JComboBox<StatusUsuario> cmbStatus = new JComboBox<>(StatusUsuario.values());
    private final JButton btnSalvar = new JButton("Salvar");
    private final JButton btnCancelar = new JButton("Cancelar");

    public UsuarioEdicaoView(Frame parent, String login, String nome, StatusUsuario status) {
        super(parent, "Editar usuário", true);

        txtLogin.setText(login);
        txtLogin.setEditable(false); // login não pode ser alterado
        txtNome.setText(nome);
        cmbStatus.setSelectedItem(status);

        montarTela();
        pack();
        setLocationRelativeTo(parent);
    }

    private void montarTela() {
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));

        addLinha(form, 0, "Login:", txtLogin);
        addLinha(form, 1, "Nome:", txtNome);
        addLinha(form, 2, "Status:", cmbStatus);

        JPanel botoes = new JPanel();
        botoes.add(btnSalvar);
        botoes.add(btnCancelar);

        btnSalvar.addActionListener(e -> salvar());
        btnCancelar.addActionListener(e -> dispose());

        setLayout(new BorderLayout());
        add(form, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);
    }

    private void addLinha(JPanel painel, int linha, String rotulo, JComponent campo) {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4);
        c.gridy = linha;

        c.gridx = 0;
        c.anchor = GridBagConstraints.EAST;
        painel.add(new JLabel(rotulo), c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        painel.add(campo, c);
    }

     private void salvar() {
        String nome = txtNome.getText().trim();
        if (nome.isEmpty()) {
            aviso("Preencha o nome.");
            return;
        }
        try {
            Usuario usuario = usuarioDao.findByLogin(txtLogin.getText());
            usuario.getPessoa().setNome(nome);
            usuario.setStatus((StatusUsuario) cmbStatus.getSelectedItem());
            usuarioDao.update(usuario);
            JOptionPane.showMessageDialog(this, "Usuário atualizado com sucesso.");
            dispose();
        } catch (UsuarioNaoEncontradoException e) {
            aviso(e.getMessage());
            dispose(); // o usuário não existe mais: a listagem recarrega
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void aviso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Atenção", JOptionPane.WARNING_MESSAGE);
    }
}