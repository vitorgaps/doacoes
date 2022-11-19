public class InteresseDoacao {
    private Usuario usuarioInteressado;
    private String justificativa;
    private ItemDoacao item;

    public InteresseDoacao(Usuario usuarioInteressado, String justificativa, ItemDoacao item) {
        this.usuarioInteressado = usuarioInteressado;
        this.justificativa = justificativa;
        this.item = item;
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


}
