package Gestoes;

import Entidades.*;
import Interfaces.IGestaoDeUsuarios;

import java.util.ArrayList;

public class GestaoDeUsuarios implements IGestaoDeUsuarios {
    private ArrayList<Usuario> listaUsuario;

    public GestaoDeUsuarios() {
        this.listaUsuario = new ArrayList<>();
        seed();
    }

    private void seed(){
        Administrador adm = new Administrador("adm123","adm@mail.com","Ademir");
        PessoaFisica vitor = new PessoaFisica("vitor123","vitor@mail.com", NivelAcesso.usuario,"Vitor Santos","12345678910");
        PessoaFisica andrei = new PessoaFisica("andrei123","andrei@mail.com", NivelAcesso.usuario,"Andrei Magalhães","12345678910");
        PessoaFisica pedro = new PessoaFisica("pedro123","pedro@mail.com", NivelAcesso.usuario,"Pedro Elias","12345678910");
        listaUsuario.add(adm);
        listaUsuario.add(vitor);
        listaUsuario.add(andrei);
        listaUsuario.add(pedro);
    }

    @Override
    public void cadastrarPessoaFisica(String email, String nome, String senha, String cpf) {
        Usuario newUser = new PessoaFisica(senha, email, NivelAcesso.usuario, nome, cpf);
        listaUsuario.add(newUser);
    }

    @Override
    public void cadastrarPessoaJuridica(String email, String nome, String senha, String cnpj) {
        Usuario newUser = new PessoaJuridica(senha, email, NivelAcesso.usuario, nome, cnpj);
        listaUsuario.add(newUser);
    }

    @Override
    public void cadastrarAdminstrador(String email, String nome, String senha) {
        Usuario newUser = new Administrador(senha, email, nome);
        listaUsuario.add(newUser);
    }

    @Override
    public void excluirUsuario(Usuario usuario) {
        listaUsuario.remove(usuario);
    }

    @Override
    public void modificarUsuario(Usuario usuario, String nome, String senha, String registro) {
        usuario.modificarDados(registro, nome, senha);
    }

    public ArrayList<Usuario> obterUsuarios(){
        return listaUsuario;
    }
}

