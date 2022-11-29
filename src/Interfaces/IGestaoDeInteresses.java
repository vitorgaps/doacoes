package Interfaces;

import Entidades.InteresseDoacao;
import Entidades.ItemDoacao;
import Entidades.Usuario;

import java.util.ArrayList;

public interface IGestaoDeInteresses {
    void demontrarInteresseDoacao(String justificativa, ItemDoacao item, Usuario usuario, int quantidade);
    ArrayList<Usuario> visualizarInteressadosDoacao(ItemDoacao item);
    ArrayList<InteresseDoacao> visualizarInteressadosPorUsuario(Usuario usuario);

    boolean aprovarInteresse(InteresseDoacao item);
}
