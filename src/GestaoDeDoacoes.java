import java.util.ArrayList;

public class GestaoDeDoacoes implements IGestaoDeDoacoes {
    private ArrayList<ItemDoacao> listaDoacoes;
    private int limiteDeDoacoesRecebidas;
    private int limiteCadastroItens;

    public GestaoDeDoacoes(int limiteDeDoacoesRecebidas, int limiteCadastroItens) {
        this.listaDoacoes = new ArrayList<>();
        this.limiteDeDoacoesRecebidas = limiteDeDoacoesRecebidas;
        this.limiteCadastroItens = limiteCadastroItens;
    }

    public ArrayList<ItemDoacao> getListaDoacoes() {
        return listaDoacoes;
    }

    public void setListaDoacoes(ArrayList<ItemDoacao> listaDoacoes) {
        this.listaDoacoes = listaDoacoes;
    }

    public int getLimiteDeDoacoesRecebidas() {
        return limiteDeDoacoesRecebidas;
    }

    public void setLimiteDeDoacoesRecebidas(int limiteDeDoacoesRecebidas) {
        this.limiteDeDoacoesRecebidas = limiteDeDoacoesRecebidas;
    }

    public int getLimiteCadastroItens() {
        return limiteCadastroItens;
    }

    public void setLimiteCadastroItens(int limiteCadastroItens) {
        this.limiteCadastroItens = limiteCadastroItens;
    }

    @Override
    public boolean cadastrarItemDoacao(String tipo, String desc, int qtd, String idUsuario, ArrayList<String> fotos, String cidade){
        if(listaDoacoes.size()+qtd > limiteCadastroItens){
            return false;
        }
        else{
            ItemDoacao item = new ItemDoacao(tipo,desc,qtd,idUsuario,fotos,cidade,StatusItemDoacao.aguardandoAprovacao);
            listaDoacoes.add(item);
            return true;
        }
    }

    @Override
    public void alterarItemDoacao(ItemDoacao item, String tipo, String desc, int qtd, String idUsuario, ArrayList<String> fotos, String cidade, StatusItemDoacao status){
        listaDoacoes.get(listaDoacoes.indexOf(item)).atualizarValores(tipo,desc,qtd,idUsuario,fotos,cidade,status);
    }

    @Override
    public boolean excluirItemDoacao(ItemDoacao item){
        return listaDoacoes.remove(item);
    }

    @Override
    public ArrayList<ItemDoacao> pesquisaItemDoacao(String descricao){
        ArrayList itensEncorntados = new ArrayList<ItemDoacao>();
        listaDoacoes.forEach((item)->{
            if(item.getDescricao().contains(descricao)){
                itensEncorntados.add(item);
            }
        });
        return itensEncorntados;
    }

    @Override
    public ArrayList<ItemDoacao> retornarListaNovosItens(){
        ArrayList<ItemDoacao> novosItens = new ArrayList<>();
        this.listaDoacoes.forEach((item)->{
            if(item.getStatus() == StatusItemDoacao.aguardandoAprovacao){
                novosItens.add(item);
            }
        });
        return novosItens;
    }

    @Override
    public void reprovarCadastroItem(ItemDoacao item){
        item.setStatus(StatusItemDoacao.reprovado);
    }

    @Override
    public void aprovarCadastroItem(ItemDoacao item){
        item.setStatus(StatusItemDoacao.aprovado);
    }

}
