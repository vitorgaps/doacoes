import java.util.ArrayList;

public class TipoItem implements ITipoItem {
    private ArrayList<String> listaTipos;

    public TipoItem(){
        listaTipos = new ArrayList<>();
        seed();
    }

    private void seed(){
        listaTipos.add("Roupas masculinas");
        listaTipos.add("Roupas femininas");
        listaTipos.add("Roupas infantis - masculinas");
        listaTipos.add("Roupa infantis - femininas");
        listaTipos.add("Brinquedos");
        listaTipos.add("Livros");
    }

    @Override
    public void inserirTipo(String tipo){
        listaTipos.add(tipo);
    }

    @Override
    public boolean excluirTipo(String tipo){
        return listaTipos.remove(tipo);
    }
    @Override
    public ArrayList<String> getListaTipos() {
        return listaTipos;
    }
}
