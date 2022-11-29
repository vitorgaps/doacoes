package Interfaces;

import java.util.ArrayList;

public interface ITipoItem {
    void inserirTipo(String tipo);

    boolean excluirTipo(String tipo);

    ArrayList<String> getListaTipos();
}
