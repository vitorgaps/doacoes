import java.util.ArrayList;

public class GestaoDeInteresses implements IGestaoDeInteresses {
    private ArrayList<InteresseDoacao> listaInteresses;


    @Override
    public void demontrarInteresseDoacao(String justificativa, ItemDoacao item, Usuario usuario){
        InteresseDoacao newInteresseDoacao = new InteresseDoacao(usuario,justificativa, item);
        listaInteresses.add(newInteresseDoacao);
    }

    @Override
    public ArrayList<Usuario> visualizarInteressadosDoacao(ItemDoacao item) {
        ArrayList<Usuario> interessados = new ArrayList<>();

        this.listaInteresses.forEach((e) -> {
            if(e.getItem() == item){
                interessados.add(e.getUsuarioInteressado());
            }
        });

        return interessados;
    }

    public ArrayList<InteresseDoacao> visualizarInteressadosPorUsuario(Usuario usuario){
        ArrayList<InteresseDoacao> interesses = new ArrayList<>();
        for (InteresseDoacao interesseDoacao: listaInteresses) {
            if(interesseDoacao.getUsuarioInteressado()==usuario){
                interesses.add(interesseDoacao);
            }
        }
        return interesses;
    }
}
