import java.util.ArrayList;

public interface IGestaoDeInteresses {
    void demontrarInteresseDoacao(String justificativa, ItemDoacao item, Usuario usuario);
    ArrayList<Usuario> visualizarInteressadosDoacao(ItemDoacao item);
    ArrayList<InteresseDoacao> visualizarInteressadosPorUsuario(Usuario usuario);
}
