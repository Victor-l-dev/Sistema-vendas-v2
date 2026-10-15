package br.com.aweb.sistema_de_vendas.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.aweb.sistema_de_vendas.model.Usuario;
import br.com.aweb.sistema_de_vendas.model.enums.UsuarioRole;
import br.com.aweb.sistema_de_vendas.service.UsuarioService;

// Cadastra os usuários de teste (ADMIN e PADRAO) na primeira execução
@Component
public class CargaInicialUsuarios implements CommandLineRunner {

    private final UsuarioService usuarioService;

    @Value("${app.usuarios.admin.email}")
    private String adminEmail;

    @Value("${app.usuarios.admin.senha}")
    private String adminSenha;

    @Value("${app.usuarios.padrao.email}")
    private String padraoEmail;

    @Value("${app.usuarios.padrao.senha}")
    private String padraoSenha;

    public CargaInicialUsuarios(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public void run(String... args) {
        criarSeNaoExistir("Administrador", adminEmail, adminSenha, UsuarioRole.ADMIN);
        criarSeNaoExistir("Vendedor Padrão", padraoEmail, padraoSenha, UsuarioRole.PADRAO);
    }

    private void criarSeNaoExistir(String nome, String email, String senha, UsuarioRole role) {
        if (!usuarioService.existePorEmail(email)) {
            usuarioService.salvar(new Usuario(nome, email, senha, role));
        }
    }
}
