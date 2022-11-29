import Entidades.*;
import Gestoes.*;

import java.util.*;

public class Main {

    private static GestaoDeUsuarios gestaoUsuarios;
    private static GestaoDeDoacoes gestaoDoacoes;
    private static GestaoDeInteresses gestaoDeInteresses;
    private static GestaoDeAcessos gestaoDeAcessos;
    private static GestaoMensagens gestaoDeMensagens;
    private static Scanner leitor;
    private static TipoItem tipoItem;

    public static void main(String[] args) {
        inicializar();
    }

    public static void inicializar(){
        gestaoDeInteresses =  Factory.getGestaoDeInteresses();
        gestaoDoacoes =  Factory.getGestaoDeDoacoes(20,20);
        gestaoUsuarios = Factory.getGestaoDeUsuarios();
        gestaoDeMensagens = Factory.getGestaoDeMensagens();
        gestaoDeAcessos = Factory.getGestaodeAcessos(gestaoUsuarios);
        tipoItem = new TipoItem();
        leitor = new Scanner(System.in);
        menuInicial();
    }

    public final static void limpaTela()
    {
        for (int i=0;i<10;i++)
            System.out.println();
    }

    public static void menuInicial(){
        limpaTela();
        int opcao = 0;
        do{
            imprimeNomeTela("Menu inicial");
            System.out.println("Boas vindas! Você gostaria de:");
            System.out.println("1 - Logar no sistema");
            System.out.println("2 - Criar novo usuário Pessoa física");
            System.out.println("3 - Criar novo usuário Pessoa jurídica");
            System.out.println("4 - Sair");
            opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao) {
                case 1:
                    logar();
                    break;
                case 2:
                    criarUsuarioPessoaFisica();
                    break;
                case 3:
                    criarUsuarioPessoaJuridica();
                    break;
                case 4:
                    break;
                default:
                    messageErrorDefault();
            }
        }while (opcao!=4);
    }

    public static void logar(){
        limpaTela();
        imprimeNomeTela("Menu de Login");

        String email = receberEntradaTextoUsuario("Digite o email: ");
        String password = receberEntradaTextoUsuario("Digite sua senha: ");
        Usuario usuario = gestaoDeAcessos.login(email,password);

        if(usuario!=null){
            if (usuario instanceof Administrador){
                menuAdministrador(usuario);
            } else{
                menuUsuario(usuario);
            }
        }
        else{
            imprimeMensagemAlerta("Usuário ou senha incorretos. Por favor tente novamente!");
        }
    }

    public static void criarUsuarioAdm(){
        limpaTela();
        imprimeNomeTela("Menu de cadastro: administrador");

        String nome = receberEntradaTextoUsuario("Digite seu nome: ");
        String email = receberEntradaTextoUsuario("Digite seu email: ");
        String password = receberEntradaTextoUsuario("Digite sua senha: ");

        gestaoUsuarios.cadastrarAdminstrador(email,nome,password);
        imprimeMensagemAlerta("Cadastro realizado com sucesso!");
    }

    public static void criarUsuarioPessoaFisica(){
        limpaTela();
        imprimeNomeTela("Menu de cadastro: pessoa física");

        String nome = receberEntradaTextoUsuario("Digite seu nome: ");
        String email = receberEntradaTextoUsuario("Digite sua email: ");
        String cpf = receberEntradaTextoUsuario("Digite seu cpf: ");
        String password = receberEntradaTextoUsuario("Digite sua senha: ");

        gestaoUsuarios.cadastrarPessoaFisica(email,nome,password,cpf);
        imprimeMensagemAlerta("Cadastro realizado com sucesso!");
    }

    public static void criarUsuarioPessoaJuridica(){
        limpaTela();
        imprimeNomeTela("Menu de cadastro: pessoa jurídica");

        String nome = receberEntradaTextoUsuario("Digite seu nome: ");
        String cnpj = receberEntradaTextoUsuario("Digite seu cnpj: ");
        String email = receberEntradaTextoUsuario("Digite sua email: ");
        String password = receberEntradaTextoUsuario("Digite sua senha: ");

        gestaoUsuarios.cadastrarPessoaJuridica(email,nome,password,cnpj);
        imprimeMensagemAlerta("Cadastro realizado com sucesso!");
    }

    public static void messageErrorDefault (){
        imprimeMensagemAlerta("Opção não encontrada!");
    }

    public static void menuAdministrador(Usuario usuario){
        limpaTela();
        imprimeNomeTela("Menu de administrador");
        int opcao = 0;
        do{
            System.out.println("Olá " + usuario.getNome() + ". Você gostaria de: ");
            System.out.println("1 - Visualizar novos itens cadastrados");
            System.out.println("2 - Criar administrador");
            System.out.println("3 - Adicionar nova categoria de doação");
            System.out.println("4 - Remover uma categoria existente");
            System.out.println("5 - Sair");
            opcao = leitor.nextInt();
            leitor.nextLine();

            if(opcao == 1){
                visualizarItensCadastrados();
            }
            if(opcao == 2){
                criarUsuarioAdm();
            }
            if(opcao == 3){
                adicionarNovoTipo();
            }
            if(opcao == 4){
                removerTipo();
            }
            if(opcao == 5){
                deslogar(usuario);
            }
        } while (opcao != 5);
    }

    private static void removerTipo() {
        limpaTela();
        imprimeNomeTela("Gerenciamento de categorias de doações: excluir");
        ArrayList<String> categoriasExistentes;
        int opcao = 0;
        do{
            System.out.println("Categorias existentes: ");
            categoriasExistentes = tipoItem.getListaTipos();
            for(int i = 0; i<categoriasExistentes.size(); i++){
                System.out.println((i)+" - "+categoriasExistentes.get(i));
            }
            System.out.println("Selecione a categoria que deseja excluir ou '"+categoriasExistentes.size()+"' para sair");
            opcao = leitor.nextInt();
            leitor.nextLine();
            if(opcao < 0 || opcao > categoriasExistentes.size()){
                System.out.println("Opção não encontrada");
            }

            else if(opcao==categoriasExistentes.size()){
                break;
            }
            else{
                String categoriaExcluida = categoriasExistentes.get(opcao);
                tipoItem.excluirTipo(categoriaExcluida);
                imprimeMensagemAlerta("Categoria '" + categoriaExcluida + "' excluída com sucesso!");
            }
        } while(opcao < 0 || opcao > categoriasExistentes.size());
    }

    private static void adicionarNovoTipo() {
        limpaTela();
        imprimeNomeTela("Gerenciamento de categorias de doações: criar");
        ArrayList<String> categoriasExistentes;
        String categoria;
        do{
            System.out.println("Categorias existentes: ");
            categoriasExistentes = tipoItem.getListaTipos();
            for(int i = 0; i<categoriasExistentes.size(); i++){
                System.out.println((i)+" - "+categoriasExistentes.get(i));
            }
            System.out.println("Digite o nome da categoria que deseja adicionar ou '1' para retornar a tela anterior");
            categoria = leitor.nextLine();

            if(categoria.equals("1")){
                break;
            }
            else{
                tipoItem.inserirTipo(categoria);
                imprimeMensagemAlerta("Categoria '" + categoria + "' inserida com sucesso!");
            }
        } while(categoria.equals("1"));
    }

    private static void visualizarItensCadastrados() {
        limpaTela();
        imprimeNomeTela("Menu de itens cadastrados");
        ArrayList<ItemDoacao> novosItens = gestaoDoacoes.retornarListaNovosItens();
        int itemSelecionado = 0;
        if(novosItens.size() > 0){
            do{
                for (int index = 0; index<novosItens.size();index++){
                    ItemDoacao item = novosItens.get(index);
                    System.out.println((index+1) + " ---------- ");
                    System.out.println("Cidade: " + item.getCidade());
                    System.out.println("Descrição: " +item.getDescricao());
                    System.out.println("Tipo: " + item.getTipo());
                    System.out.println("Quantidade: " + item.getQuantidade());
                }
                System.out.println("Selecione um item ou digite '"+(novosItens.size()+1)+"' para sair:");
                itemSelecionado = leitor.nextInt();
                leitor.nextLine();

                if(itemSelecionado==novosItens.size()+1){
                    break;
                }
                if(itemSelecionado > 0 && itemSelecionado <= novosItens.size()){
                    System.out.println("Item " + itemSelecionado + " selecionado.");

                    int opcao = 0;
                    do{
                        System.out.println("Digite: ");
                        System.out.println("1 - Aprovar item");
                        System.out.println("2 - Reprovar item");
                        System.out.println("3 - Voltar");
                        System.out.println("4 - Sair");
                        opcao = leitor.nextInt();
                        leitor.nextLine();
                    } while (opcao < 0 || opcao > 4);
                    ItemDoacao novoItemSelecionado = novosItens.get(itemSelecionado-1);
                    String emailDestinatario = novoItemSelecionado.getIdUsuario();

                    switch (opcao){
                        case 1:
                            gestaoDoacoes.aprovarCadastroItem(novoItemSelecionado);
                            gestaoDeMensagens.enviaEmailAprovacaoCadastro(emailDestinatario,novoItemSelecionado);
                            imprimeMensagemAlerta("Aprovado com sucesso!");
                            break;
                        case 2:
                            System.out.println("Informe a justificativa para a reprovação: ");
                            String justificativa = leitor.nextLine();
                            gestaoDoacoes.reprovarCadastroItem(novoItemSelecionado);
                            gestaoDeMensagens.enviaEmailReprovacaoCadastro(emailDestinatario,novoItemSelecionado,justificativa);
                            imprimeMensagemAlerta("Reprovado com sucesso!");
                            break;
                        case 3:
                            visualizarItensCadastrados();
                            break;
                        case 4:
                            break;
                        default:
                            messageErrorDefault();
                    }
                }
            } while (itemSelecionado < 0 || itemSelecionado > novosItens.size());
        } else {
            imprimeMensagemAlerta("Nenhum novo item cadastrado.");
        }
    }

    private static void deslogar(Usuario usuario) {
        gestaoDeAcessos.logout(usuario);
    }

    public static void menuUsuario(Usuario usuario){
        limpaTela();
        imprimeNomeTela("Menu de usuário");
        int opcao = 0;
        do{
            System.out.println("Olá " + usuario.getNome() + ". Você gostaria de:");
            System.out.println("1 - Cadastrar Item para Doação");
            System.out.println("2 - Pesquisar Itens de Doação");
            System.out.println("3 - Visualizar Interessados em Doação");
            System.out.println("4 - Sair");

            opcao = leitor.nextInt();
            leitor.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarItemDoacao();
                    break;
                case 2:
                    pesquisaItensDoacao(usuario);
                    break;
                case 3:
                    visualizarInteressadosDoacao(usuario);
                    break;
                case 4:
                    deslogar(usuario);
                    break;
                default:
                    messageErrorDefault();
            }
        }while (opcao!=4);
    }

    public static void cadastrarItemDoacao(){
        limpaTela();
        imprimeNomeTela("Menu de cadastro: novo item");

        int tipoItemValor = 0;
        do{
            System.out.println("Tipos disponíveis: ");
            for(int index=0;index<tipoItem.getListaTipos().size();index++){
                System.out.printf("%d - %s \n",index,tipoItem.getListaTipos().get(index));
            }

            System.out.println("Digite o tipo: ");
            tipoItemValor = leitor.nextInt();
            leitor.nextLine();

            if(tipoItemValor < 0 || tipoItemValor > tipoItem.getListaTipos().size()-1){
                imprimeMensagemAlerta("Tipo inválido");
            }
        } while (tipoItemValor < 0 || tipoItemValor > tipoItem.getListaTipos().size()-1);

        String tipoItemEscolhido = tipoItem.getListaTipos().get(tipoItemValor);
        System.out.println("Tipo escolhido: "+ tipoItemEscolhido + " \n");

        String desc = receberEntradaTextoUsuario("Digite a descrição: ");
        String idUsuario = receberEntradaTextoUsuario("Digite um e-mail para contato: ");
        String cidade = receberEntradaTextoUsuario("Digite sua cidade: ");

        System.out.println("Digite a quantidade: ");
        int qtd = leitor.nextInt();
        leitor.nextLine();

        ArrayList<String> foto = new ArrayList<>();

        boolean sucesso = gestaoDoacoes.cadastrarItemDoacao(tipoItemEscolhido,desc,qtd,idUsuario,foto,cidade);

        if (sucesso == true){
            imprimeMensagemAlerta("Item cadastrado com sucesso !");
        }
    }

    public static void pesquisaItensDoacao(Usuario usuario){
        limpaTela();
        imprimeNomeTela("Menu de pesquisa: itens de doação");
        int opcao = 0;
        ArrayList<ItemDoacao> doacoes;
        do{
            System.out.println("Digite uma descrição do item que deseja procurar: ");
            String descricao = leitor.nextLine();
            doacoes = gestaoDoacoes.pesquisaItemDoacao(descricao);

            if(doacoes.size()==0){
                imprimeMensagemAlerta("Nenhum item encontrado com a descrição informada, tente novamente por favor");
            }
            else{
                System.out.println("Itens encontrados:");
                for(int index=0;index<doacoes.size();index++){
                    ItemDoacao doacao = doacoes.get(index);
                    System.out.println(index + " - " + doacao.getDescricao());
                    System.out.println("--------------------------------------");
                    System.out.println("Tipo - " + doacao.getTipo());
                    System.out.println("Cidade - " + doacao.getCidade());
                    System.out.println("Quantidade disponível - " + doacao.getQuantidade());
                    System.out.println("\n");
                }
                System.out.println("Selecione o item desejado para demonstrar interesse ou '" + doacoes.size()+ "' para voltar");
                opcao = leitor.nextInt();
                leitor.nextLine();
                if(opcao!=doacoes.size()){
                    if (opcao < 0 || opcao > doacoes.size()){
                        imprimeMensagemAlerta("Opção não encontrada. Tente novamente!");
                    }
                    else{
                        ItemDoacao item = doacoes.get(opcao);
                        System.out.println("Digite uma justificativa para o interesse: ");
                        String justificativa = leitor.nextLine();
                        System.out.println("Qual a quantidade de itens você deseja? (Máximo: "+item.getQuantidade()+")");
                        int quantidade = leitor.nextInt();
                        leitor.nextLine();
                        quantidade = Math.max(1,Math.min(quantidade, item.getQuantidade()));
                        System.out.println("Resumo do interesse: ");
                        System.out.println("Quantidade: " + quantidade);
                        System.out.println("Justificativa: " + justificativa);
                        gestaoDeInteresses.demontrarInteresseDoacao(justificativa,item,usuario,quantidade);
                        imprimeMensagemAlerta("Interesse registrado com sucesso!");
                        break;
                    }
                }
            }
        } while (opcao != doacoes.size());
    }

    public static void visualizarInteressadosDoacao(Usuario usuario){
        limpaTela();
        imprimeNomeTela("Menu de interesses: lista de interessados");
        ArrayList<InteresseDoacao> interesses;
        int opcao = 0;
        do{
            interesses = gestaoDeInteresses.visualizarInteressadosPorUsuario(usuario);
            if(interesses.size()==0){
                imprimeMensagemAlerta("Nenhum interessado encontrado");
                break;
            }
            else{
                for(int index = 0;index<interesses.size();index++){
                    InteresseDoacao item = interesses.get(index);
                    System.out.println(index + " - " + item.getItem().getDescricao());
                    System.out.println("--------------------------------------");
                    System.out.println("Interessado - " + item.getUsuarioInteressado().getNome());
                    System.out.println("Justificativa - " + item.getJustificativa());
                    System.out.println("Quantidade - " + item.getQuantidade());
                    System.out.println("\n");
                }
                System.out.println("Selecione o interessado ou digite '" + interesses.size() + "' para sair");
                opcao = leitor.nextInt();
                leitor.nextLine();
                if(opcao!=interesses.size()){
                    InteresseDoacao item = interesses.get(opcao);
                    int valor;
                    do{
                        System.out.println("Selecionado: ");
                        System.out.println(opcao + " - " + item.getItem().getDescricao());
                        System.out.println("Interessado - " + item.getUsuarioInteressado().getNome()+"\n");
                        System.out.println("--------------------------------------");
                        System.out.println("Digite: ");
                        System.out.println("1 - Aprovar");
                        System.out.println("2 - Voltar");
                        valor = leitor.nextInt();
                        leitor.nextLine();
                        if(valor==1){
                            boolean sucesso = gestaoDeInteresses.aprovarInteresse(item);
                            if(sucesso){
                                gestaoDeMensagens.enviaEmailAprovacaoDoacao(item.getUsuarioInteressado().getEmail(),item);
                            }
                        }
                    } while(valor!=1 && valor != 2);
                }
            }
        }while(opcao!=interesses.size());
    }

    public final static void imprimeMensagemAlerta(String mensagem){
        System.out.printf("\n\n* " + mensagem + " *\n\n");
    }

    public final static void imprimeNomeTela(String nome){
        System.out.println("---------- " + nome + " ----------");
    }

    public static String receberEntradaTextoUsuario(String mensagem){
        System.out.println(mensagem);
        String valorLido = leitor.nextLine();
        return valorLido;
    }
}
