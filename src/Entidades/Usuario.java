package Entidades;

public class Usuario {
     private String senha;
     private String email;
     private NivelAcesso nivelAcesso;
     private boolean logado;

     public Usuario(String senha, String email, NivelAcesso nivelAcesso, String nome) {
          this.senha = senha;
          this.email = email;
          this.nivelAcesso = nivelAcesso;
          this.nome = nome;
          this.logado = false;
     }

     public String getNome() {
          return nome;
     }

     public void setNome(String nome) {
          this.nome = nome;
     }

     private String nome = "";

     public String getSenha() {
          return senha;
     }

     public void setSenha(String senha) {
          this.senha = senha;
     }

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     public NivelAcesso getNivelAcesso() {
          return nivelAcesso;
     }

     public void setNivelAcesso(NivelAcesso nivelAcesso) {
          this.nivelAcesso = nivelAcesso;
     }

     public void setLogado(boolean logado) {
          this.logado = logado;
     }

     public void modificarDados(String registro, String nome, String senha) {
     }

}
