public class PessoaJuridica extends Usuario{
    private String cnpj = "";

    public PessoaJuridica(String senha, String email, NivelAcesso nivelAcesso, String nome, String cnpj) {
        super(senha, email, nivelAcesso, nome);
        this.cnpj = cnpj;
    }

    public void modificarDados(String registro, String nome, String senha) {
        this.cnpj = registro;
        setNome(nome);
        setSenha(senha);
    }
}
