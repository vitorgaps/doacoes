package Gestoes;

import Entidades.InteresseDoacao;
import Entidades.ItemDoacao;
import Interfaces.IGestaoMensagens;

public class GestaoMensagens implements IGestaoMensagens {

    public void enviaEmail(String email){
        System.out.println("\n------------------------------");
        System.out.println(email);
        System.out.println("------------------------------");
    }

    @Override
    public void enviaEmailAprovacaoCadastro(String emailDestinatario, ItemDoacao item) {
        enviaEmail(
                "O cadastro para o item abaixo foi aprovado e agora está disponível no sistema: "+
                "\nDescrição: " + item.getDescricao() +
                "\nTipo: " + item.getTipo() +
                "\nQuantidade: " + item.getQuantidade() +
                "\nCidade: " + item.getCidade() +
                "\nPara -> " + emailDestinatario
        );
    }

    @Override
    public void enviaEmailReprovacaoCadastro(String emailDestinatario, ItemDoacao item, String justificativa) {
        enviaEmail(
                "O cadastro para o item abaixo foi reprovado: "+
                        "\nJustificativa: " + justificativa +
                        "\nDescrição: " + item.getDescricao() +
                        "\nTipo: " + item.getTipo() +
                        "\nQuantidade: " + item.getQuantidade() +
                        "\nCidade: " + item.getCidade() +
                        "\nPara -> " + emailDestinatario
        );
    }

    @Override
    public void enviaEmailAprovacaoDoacao(String emailDestinatario, InteresseDoacao interesse) {
        enviaEmail(
                "Sua solicitação de doação do item abaixo foi aprovada"+
                        "\nDescrição: " + interesse.getItem().getDescricao() +
                        "\nTipo: " + interesse.getItem().getTipo() +
                        "\nQuantidade: " + interesse.getQuantidade() +
                        "\nCidade: " + interesse.getItem().getCidade() +
                        "\nPara -> " + emailDestinatario
        );
    }

    @Override
    public void enviaEmailReprovacaoDoacao(String emailDestinatario, InteresseDoacao interesse) {
        enviaEmail(
                "Sua solicitação de doação do item abaixo foi aprovada"+
                        "\nDescrição: " + interesse.getItem().getDescricao() +
                        "\nTipo: " + interesse.getItem().getTipo() +
                        "\nQuantidade: " + interesse.getQuantidade() +
                        "\nCidade: " + interesse.getItem().getCidade() +
                        "\nPara -> " + emailDestinatario
        );
    }
}
