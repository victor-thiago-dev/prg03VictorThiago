package br.com.ifba.usuario.view;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import br.com.ifba.usuario.entity.StatusUsuario;
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

    protected final JTextField txtNome = new JTextField(20);
    protected final JTextField txtCpf = new JTextField(20);
    protected final JTextField txtTelefone = new JTextField(20);
    protected final JTextField txtEmail = new JTextField(20);
    protected final JTextField txtLogin = new JTextField(20);
    protected final JPasswordField txtSenha = new JPasswordField(20);
    protected final JPasswordField txtConfirmarSenha = new JPasswordField(20);
    protected final JComboBox<StatusUsuario> cmbStatus = new JComboBox<>(StatusUsuario.values());
    protected final JButton btnSalvar = new JButton("Salvar");
    protected final JButton btnCancelar = new JButton("Cancelar");

    // a edição muda para false: lá a senha só é validada se for preenchida
    protected boolean exigirSenha = true;

    public UsuarioCadastroView(Frame parent) {
        this(parent, "Cadastrar usuário");
    }

    protected UsuarioCadastroView(Frame parent, String titulo) {
        super(parent, titulo, true);
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

    // só valida os campos; a gravação no banco fica para a Parte 2
    private void salvar() {
        String senha = new String(txtSenha.getPassword());
        String confirmar = new String(txtConfirmarSenha.getPassword());

        if (!ValidadorUsuario.camposPreenchidos(txtNome.getText(), txtCpf.getText(),
                txtTelefone.getText(), txtEmail.getText(), txtLogin.getText())) {
            aviso("Preencha todos os campos.");
            return;
        }
        if (!ValidadorUsuario.cpfValido(txtCpf.getText())) {
            aviso("CPF inválido.");
            return;
        }
        if (exigirSenha || !senha.isEmpty()) {
            if (!ValidadorUsuario.senhaForte(senha)) {
                aviso("A senha deve ter no mínimo " + ValidadorUsuario.TAMANHO_MINIMO_SENHA + " caracteres.");
                return;
            }
            if (!ValidadorUsuario.senhasIguais(senha, confirmar)) {
                aviso("As senhas não conferem.");
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Dados válidos. A gravação será feita na Parte 2.");
        dispose();
    }

    private void aviso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Atenção", JOptionPane.WARNING_MESSAGE);
    }
}