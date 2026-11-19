package br.com.ifba.usuario.view;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import br.com.ifba.infrastructure.exception.NegocioException;
import br.com.ifba.infrastructure.exception.PersistenciaException;
import br.com.ifba.pessoa.entity.Cliente;
import br.com.ifba.usuario.controller.UsuarioIController;
import br.com.ifba.usuario.entity.StatusUsuario;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.validar.ValidadorUsuario;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UsuarioCadastroView extends JDialog {

    private final UsuarioIController controller;

    private final JTextField txtNome = new JTextField(20);
    private final JTextField txtCpf = new JTextField(20);
    private final JTextField txtTelefone = new JTextField(20);
    private final JTextField txtEmail = new JTextField(20);
    private final JTextField txtLogin = new JTextField(20);
    private final JPasswordField txtSenha = new JPasswordField(20);
    private final JPasswordField txtConfirmarSenha = new JPasswordField(20);
    private final JComboBox<StatusUsuario> cmbStatus = new JComboBox<>(StatusUsuario.values());
    private final JButton btnSalvar = new JButton("Salvar");
    private final JButton btnCancelar = new JButton("Cancelar");

    public UsuarioCadastroView(Frame parent, UsuarioIController controller) {
        super(parent, "Cadastrar usuário", true);
        this.controller = controller; // recebe, não cria
        montarTela();
        pack();
        setLocationRelativeTo(parent);
    }

    private void montarTela() {
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));

        addLinha(form, 0, "Nome:", txtNome);
        addLinha(form, 1, "CPF:", txtCpf);
        addLinha(form, 2, "Telefone:", txtTelefone);
        addLinha(form, 3, "E-mail:", txtEmail);
        addLinha(form, 4, "Login:", txtLogin);
        addLinha(form, 5, "Senha:", txtSenha);
        addLinha(form, 6, "Confirmar senha:", txtConfirmarSenha);
        addLinha(form, 7, "Status:", cmbStatus);

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
        String cpf = txtCpf.getText().trim();
        String telefone = txtTelefone.getText().trim();
        String email = txtEmail.getText().trim();
        String login = txtLogin.getText().trim();
        String senha = new String(txtSenha.getPassword());
        String confirmar = new String(txtConfirmarSenha.getPassword());

        // validações de tela: só feedback imediato (a garantia está no Service)
        if (!ValidadorUsuario.camposPreenchidos(nome, cpf, telefone, email, login, senha)) {
            aviso("Preencha todos os campos.");
            return;
        }
        if (!ValidadorUsuario.senhasIguais(senha, confirmar)) {
            aviso("As senhas não conferem.");
            return;
        }

        try {
            Usuario usuario = new Usuario(new Cliente(nome, cpf, telefone, email), login, senha);
            usuario.setStatus((StatusUsuario) cmbStatus.getSelectedItem());
            controller.save(usuario);
            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso.");
            dispose();
        } catch (NegocioException e) {
            aviso(e.getMessage());
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void aviso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Atenção", JOptionPane.WARNING_MESSAGE);
    }
}