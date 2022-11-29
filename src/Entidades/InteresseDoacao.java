package Entidades;

public class InteresseDoacao {
    private Usuario usuarioInteressado;
    private String justificativa;
    private ItemDoacao item;
    private int quantidade;

    public InteresseDoacao(Usuario usuarioInteressado, String justificativa, ItemDoacao item, int quantidade) {
        this.usuarioInteressado = usuarioInteressado;
        this.justificativa = justificativa;
        this.item = item;
        this.quantidade = quantidade;
    }

    public Usuario getUsuarioInteressado() {
        return usuarioInteressado;
    }

    public void setUsuarioInteressado(Usuario usuarioInteressado) {
        this.usuarioInteressado = usuarioInteressado;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public ItemDoacao getItem() {
        return item;
    }

    public void setItem(ItemDoacao item) {
        this.item = item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
