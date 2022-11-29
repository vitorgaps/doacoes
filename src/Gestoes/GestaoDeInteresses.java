package Gestoes;

import Entidades.InteresseDoacao;
import Entidades.ItemDoacao;
import Entidades.StatusItemDoacao;
import Entidades.Usuario;
import Interfaces.IGestaoDeInteresses;

import java.util.ArrayList;

public class GestaoDeInteresses implements IGestaoDeInteresses {
    private ArrayList<InteresseDoacao> listaInteresses = new ArrayList<>();


    @Override
    public void demontrarInteresseDoacao(String justificativa, ItemDoacao item, Usuario usuario, int quantidade){
        InteresseDoacao newInteresseDoacao = new InteresseDoacao(usuario,justificativa, item, quantidade);
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
            if(interesseDoacao.getItem().getIdUsuario().equals(usuario.getEmail())){
                interesses.add(interesseDoacao);
            }
        }
        return interesses;
    }

    @Override
    public boolean aprovarInteresse(InteresseDoacao item) {
        if(item.getQuantidade() > item.getItem().getQuantidade()){
            return false;
        }
        else{
            item.getItem().setQuantidade(item.getItem().getQuantidade()-item.getQuantidade());
            if(item.getItem().getQuantidade()==0){
                item.getItem().setStatus(StatusItemDoacao.doado);
                listaInteresses.remove(item);
            }
            return true;
        }
    }
}
