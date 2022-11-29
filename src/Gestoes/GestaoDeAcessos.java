package Gestoes;

import Entidades.Usuario;
import Interfaces.IGestaoDeAcessos;
import Interfaces.IGestaoDeUsuarios;

import java.util.ArrayList;

public class GestaoDeAcessos implements IGestaoDeAcessos {
    private IGestaoDeUsuarios gestaoDeUsuarios;
    private ArrayList<Usuario> sessoes;

    public GestaoDeAcessos(IGestaoDeUsuarios gestaoDeUsuarios) {
        this.gestaoDeUsuarios = gestaoDeUsuarios;
        this.sessoes = new ArrayList<>();
    }

    @Override
    public Usuario login(String email, String senha){
      ArrayList<Usuario> usuarios = gestaoDeUsuarios.obterUsuarios();
        for (Usuario usuario: usuarios
             ) {
            if(usuario.getEmail().equals(email))
                if(usuario.getSenha().equals(senha)){
                    if(!sessoes.contains(usuario)){
                        sessoes.add(usuario);
                        usuario.setLogado(true);
                    }
                    return usuario;
                }
        }
        return null;
    };

    public boolean logout(Usuario usuario){
        usuario.setLogado(false);
        return sessoes.remove(usuario);
    }
}
