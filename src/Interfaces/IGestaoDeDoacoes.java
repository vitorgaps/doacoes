package Interfaces;

import Entidades.ItemDoacao;
import Entidades.StatusItemDoacao;

import java.util.ArrayList;

public interface IGestaoDeDoacoes {
    boolean cadastrarItemDoacao(String tipo, String desc, int qtd, String idUsuario, ArrayList<String> fotos, String cidade);

    void alterarItemDoacao(ItemDoacao item, String tipo, String desc, int qtd, String idUsuario, ArrayList<String> fotos, String cidade, StatusItemDoacao status);

    boolean excluirItemDoacao(ItemDoacao item);

    ArrayList<ItemDoacao> pesquisaItemDoacao(String descricao);

    ArrayList<ItemDoacao> retornarListaNovosItens();

    void reprovarCadastroItem(ItemDoacao item);

    void aprovarCadastroItem(ItemDoacao item);
}
