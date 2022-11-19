import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner; // 1. importando a classe Scanner

public class Main {

    private static GestaoDeUsuarios gestaoUsuarios;
    private static GestaoDeDoacoes gestaoDoacoes;
    private static GestaoDeInteresses gestaoDeInteresses;
    private static GestaoDeAcessos gestaoDeAcessos;
    private static Scanner leitor;

    public static void main(String[] args) {
        inicializar();
    }

    public static void inicializar(){
        gestaoDeInteresses =  Factory.getGestaoDeInteresses();
        gestaoDoacoes =  Factory.getGestaoDeDoacoes(20,20);
        gestaoUsuarios = Factory.getGestaoDeUsuarios();
        gestaoDeAcessos = Factory.getGestaodeAcessos(gestaoUsuarios);
        leitor = new Scanner(System.in);
        menuInicial();
    }

    public final static void limpaTela()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            System.out.println();
        }
    }

    public static void menuInicial(){
        limpaTela();
        int op = 0;

        do{
            System.out.println("Bem vindo! Você gostaria de:");
            System.out.println("1 - Logar no sistema");
            System.out.println("2 - Criar novo usuário Pessoa física");
            System.out.println("3 - Criar novo usuário Pessoa jurídica");
            System.out.println("4 - Sair");
            op = leitor.nextInt();

            switch (op) {
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
        }while (op!=4);
    }

    public static void logar(){
        limpaTela();
        System.out.println("********** Menu de Login **********\n");
        leitor.nextLine();

        System.out.println("Digite o email: ");
        String email = leitor.nextLine();

        System.out.println("Digite sua senha: ");
        String password = leitor.nextLine();

        Usuario usuario = gestaoDeAcessos.login(email,password);

        if(usuario!=null){
            if (usuario instanceof Administrador){
                menuAdministrador(usuario);
            } else{
                menuUsuario(usuario);
            }
        }
        else{
            System.out.println("Usuário ou senha incorretos. Tente novamente!");
        }
    }

    public static void criarUsuarioAdm(){
        leitor.nextLine();

        System.out.println("Digite seu nome: ");
        String nome = leitor.nextLine();

        System.out.println("Digite sua email: ");
        String email = leitor.nextLine();

        System.out.println("Digite sua senha: ");
        String password = leitor.nextLine();

        gestaoUsuarios.cadastrarAdminstrador(email,nome,password);
        logar();
    }

    public static void criarUsuarioPessoaFisica(){
        leitor.nextLine();

        System.out.println("Digite seu nome: ");
        String nome = leitor.nextLine();

        System.out.println("Digite sua email: ");
        String email = leitor.nextLine();

        System.out.println("Digite seu cpf: ");
        String cpf = leitor.nextLine();

        System.out.println("Digite sua senha: ");
        String password = leitor.nextLine();

        gestaoUsuarios.cadastrarPessoaFisica(email,nome,password,cpf);
        logar();
    }

    public static void criarUsuarioPessoaJuridica(){
        leitor.nextLine();

        System.out.println("Digite sua senha: ");
        String password = leitor.nextLine();

        System.out.println("Digite sua email: ");
        String email = leitor.nextLine();

        System.out.println("Digite seu nome: ");
        String nome = leitor.nextLine();

        System.out.println("Digite seu cnpj: ");
        String cnpj = leitor.nextLine();
        gestaoUsuarios.cadastrarPessoaJuridica(email,nome,password,cnpj);
        logar();
    }

    public static void messageErrorDefault (){
        System.out.println("Opção não encontrada!");
    }

    public static void menuAdministrador(Usuario usuario){
        limpaTela();
        int opt=0;
        do{
            System.out.println("Bem vindo! Você gostaria de: ");
            System.out.println("1 - Visualizar novos itens cadastrados");
            System.out.println("2 - Criar administrador");
            System.out.println("3 - Sair");
            opt = leitor.nextInt();
            switch (opt) {
                case 1:
                    visualizarItensCadastrados();
                    break;
                case 2:
                    criarUsuarioAdm();
                    break;
                case 3:
                    deslogar(usuario);
                    break;
                default:
                    messageErrorDefault();
            }
        } while (opt != 3);
    }

    private static void visualizarItensCadastrados() {
        ArrayList<ItemDoacao> novosItens = gestaoDoacoes.retornarListaNovosItens();
        leitor.nextLine();
        int itemSelecionado = 0;
        if(novosItens.size() > 0){
            do{
                System.out.println("Selecione um item:");
                for (int index = 0; index<novosItens.size();index++){
                    ItemDoacao item = novosItens.get(index);
                    System.out.println((index+1) + " ---------- ");
                    System.out.println("Cidade: " + item.getCidade());
                    System.out.println("Descrição: " +item.getDescricao());
                    System.out.println("Tipo: " + item.getTipo());
                    System.out.println("Quantidade: " + item.getQuantidade());
                }
                itemSelecionado = leitor.nextInt();
            } while (itemSelecionado < 0 || itemSelecionado > novosItens.size());

            System.out.println("Item " + itemSelecionado + " selecionado.");

            int opcao = 0;
            do{
                System.out.println("Digite: ");
                System.out.println("1 - Aprovar item");
                System.out.println("2 - Reprovar item");
                System.out.println("3 - Voltar");
                System.out.println("4 - Sair");
            } while (opcao < 0 || opcao > 4);

            switch (opcao){
                case 1:
                    novosItens.get(itemSelecionado-1).setStatus(StatusItemDoacao.aprovado);
                    //Colocar lógica de aprovação
                    break;
                case 2:
                    novosItens.get(itemSelecionado-1).setStatus(StatusItemDoacao.reprovado);
                    //Colocar lógica de reprovação
                    break;
                case 3:
                    visualizarItensCadastrados();
                    break;
                case 4:
                    break;
                default:
                    messageErrorDefault();
            }
        } else {
            System.out.println("\n\nNenhum novo item cadastrado.\n\n");
        }
    }

    private static void deslogar(Usuario usuario) {
        gestaoDeAcessos.logout(usuario);
    }

    public static void menuUsuario(Usuario usuario){
        int opt = 0;
        do{
            System.out.println("Bem vindo! Você gostaria de:");
            System.out.println("1 - Cadastrar Item para Doação");
            System.out.println("2 - Pesquisar Itens de Doação");
            System.out.println("3 - Visualizar Interessados em Doação");
            System.out.println("4 - Sair");

            opt = leitor.nextInt();
            leitor.nextLine();
            switch (opt) {
                case 1:
                    cadastrarItemDoacao();
                    break;
                case 2:
                    pesquisaItensDoacao();
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
        }while (opt!=4);
    }

    public static void cadastrarItemDoacao(){
        System.out.println("********** Cadastro de novo item **********");
        leitor.nextLine();
        TipoItem tipoItem = new TipoItem();
        System.out.println("Tipos disponíveis: ");
        for(int index=0;index<tipoItem.getListaTipos().size();index++){
            System.out.printf("%d - %s \n",index,tipoItem.getListaTipos().get(index));
        }

        System.out.println("Digite o tipo: ");
        String tipItem = leitor.nextLine();

        System.out.println("Digite a descrição: ");
        String desc = leitor.nextLine();

        System.out.println("Digite seu Email: ");
        String idUsuario = leitor.nextLine();

        ArrayList<String> foto = new ArrayList<>();

        System.out.println("Digite sua cidade: ");
        String cidade = leitor.nextLine();

        System.out.println("Digite a quantidade: ");
        int qtd = leitor.nextInt();
        leitor.nextLine();

        boolean ret = gestaoDoacoes.cadastrarItemDoacao(tipItem,desc,qtd,idUsuario,foto,cidade);

        if (ret == true){
            System.out.println("Item cadastrado com sucesso !");
        }
    };

    public static void pesquisaItensDoacao(){
        System.out.println("Digite uma descrição do Item que deseja pesquisar: ");
        String descricao = leitor.nextLine();
        ArrayList doacoes = gestaoDoacoes.pesquisaItemDoacao(descricao);
        //Percorrer os itens de doação e fazer a lógica de demonstrar interesse
        System.out.println(doacoes);
    }

    public static void visualizarInteressadosDoacao(Usuario usuario){
        System.out.println("Lista de usuários interessados nos Itens doados por você: ");

        gestaoDeInteresses.visualizarInteressadosPorUsuario(usuario);
    }

}
