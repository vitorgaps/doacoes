package Entidades;

import Entidades.NivelAcesso;
import Entidades.Usuario;

public class Administrador extends Usuario {

    public Administrador(String senha, String email, String nome) {
        super(senha, email, NivelAcesso.administrador, nome);
    }

    public void modificarDados(String registro, String nome, String senha) {
        setNome(nome);
        setSenha(senha);
    }
}
