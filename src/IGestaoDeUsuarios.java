import java.util.ArrayList;

public interface IGestaoDeUsuarios {
    void cadastrarPessoaFisica(String email, String nome, String senha, String cpf);

    void cadastrarPessoaJuridica(String email, String nome, String senha, String cnpj);

    void cadastrarAdminstrador(String email, String nome, String senha);

    void excluirUsuario(Usuario usuario);

    void modificarUsuario(Usuario usuario, String nome, String senha, String registro);

    ArrayList<Usuario> obterUsuarios();
}
