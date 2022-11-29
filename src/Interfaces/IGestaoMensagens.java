package Interfaces;

import Entidades.InteresseDoacao;
import Entidades.ItemDoacao;

public interface IGestaoMensagens {
    void enviaEmailAprovacaoCadastro(String emailDestinatario, ItemDoacao item);
    void enviaEmailReprovacaoCadastro(String emailDestinatario, ItemDoacao item, String justificativa);
    void enviaEmailAprovacaoDoacao(String emailDestinatario, InteresseDoacao interesse);
    void enviaEmailReprovacaoDoacao(String emailDestinatario, InteresseDoacao interesse);
}
