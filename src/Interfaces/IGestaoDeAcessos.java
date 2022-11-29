package Interfaces;

import Entidades.Usuario;

public interface IGestaoDeAcessos {
    Usuario login(String email, String senha);
    boolean logout(Usuario usuario);
}
