import java.util.ArrayList;

public interface IItemDoacao {
    void atualizarValores(String tipo, String descricao, int quantidade, String idUsuario, ArrayList<String> fotos, String cidade, StatusItemDoacao status);
}
