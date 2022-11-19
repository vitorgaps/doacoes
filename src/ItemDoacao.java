import java.util.*;

public class ItemDoacao implements IItemDoacao {
    private String tipo = "";
    private String descricao = "";
    private int quantidade = 0;
    private String idUsuario = "";
    private ArrayList<String> fotos;
    private String cidade = "";
    private StatusItemDoacao status;

    public ItemDoacao(String tipo, String descricao, int quantidade, String idUsuario, ArrayList<String> fotos, String cidade, StatusItemDoacao status) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.idUsuario = idUsuario;
        this.fotos = fotos;
        this.cidade = cidade;
        this.status = status;
    }

    @Override
    public void atualizarValores(String tipo, String descricao, int quantidade, String idUsuario, ArrayList<String> fotos, String cidade, StatusItemDoacao status){
        this.tipo = tipo;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.idUsuario = idUsuario;
        this.fotos = fotos;
        this.cidade = cidade;
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public ArrayList<String> getFotos() {
        return fotos;
    }

    public String getCidade() {
        return cidade;
    }

    public StatusItemDoacao getStatus() {
        return status;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setFotos(ArrayList<String> fotos) {
        this.fotos = fotos;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setStatus(StatusItemDoacao status) {
        this.status = status;
    }
}
