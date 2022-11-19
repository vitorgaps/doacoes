public class Factory {

    public static GestaoDeDoacoes getGestaoDeDoacoes(int limiteDoacoes, int limiteCadastro){
        return new GestaoDeDoacoes(limiteDoacoes,limiteCadastro);
    }

    public static GestaoDeInteresses getGestaoDeInteresses(){
        return new GestaoDeInteresses();
    }

    public static GestaoDeUsuarios getGestaoDeUsuarios(){
        return new GestaoDeUsuarios();
    }

    public static GestaoDeAcessos getGestaodeAcessos(IGestaoDeUsuarios gestaoDeUsuarios){
        return new GestaoDeAcessos(gestaoDeUsuarios);
    }

    public TipoItem getTipoItem(){
        return new TipoItem();
    }

}
