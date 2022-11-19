public class PessoaFisica extends Usuario {
    private String cpf = "";

    public PessoaFisica(String senha, String email, NivelAcesso nivelAcesso, String nome, String cpf) {
        super(senha, email, nivelAcesso, nome);
        this.cpf = cpf;
    }
    public void modificarDados(String registro, String nome, String senha) {
        this.cpf = registro;
        this.setNome(nome);
        this.setSenha(senha);
    }
}
